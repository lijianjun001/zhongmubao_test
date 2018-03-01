package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.Activity;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.config.enmu.Platform;
import com.zhongmubao.api.config.enmu.ShareSuccessType;
import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.system.ShareInfoViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.PlatformTrackingMongoDao;
import com.zhongmubao.api.mongo.dao.ShareContentMongoDao;
import com.zhongmubao.api.mongo.dao.SystemServerActionMongoDao;
import com.zhongmubao.api.mongo.dao.TouTiaoAdvMongoDao;
import com.zhongmubao.api.mongo.entity.PlatformTrackingMongo;
import com.zhongmubao.api.mongo.entity.ShareContentMongo;
import com.zhongmubao.api.mongo.entity.TouTiaoAdvMongo;
import com.zhongmubao.api.service.SystemService;
import com.zhongmubao.api.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;

/**
 * 系统服务实现
 *
 * @author 孙阿龙
 */
@Service
public class SystemServiceImpl extends BaseService implements SystemService {

    private final TouTiaoAdvMongoDao touTiaoAdvMongoDao;
    private final PlatformTrackingMongoDao platformTrackingMongoDao;
    private final SystemServerActionMongoDao systemServerActionMongoDao;
    private final ShareContentMongoDao shareContentMongoDao;

    @Autowired
    public SystemServiceImpl(TouTiaoAdvMongoDao touTiaoAdvMongoDao, PlatformTrackingMongoDao platformTrackingMongoDao, SystemServerActionMongoDao systemServerActionMongoDao, ShareContentMongoDao shareContentMongoDao) {
        this.touTiaoAdvMongoDao = touTiaoAdvMongoDao;
        this.platformTrackingMongoDao = platformTrackingMongoDao;
        this.systemServerActionMongoDao = systemServerActionMongoDao;
        this.shareContentMongoDao = shareContentMongoDao;
    }

    @Override
    public void touTiaoAdv(TouTiaoAdvRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
//        if (null == model.getMac()) {
//            throw new ApiException(ResultStatus.PARAMETER_MISSING);
//        }
//        if (null == model.getImei()) {
//            throw new ApiException(ResultStatus.PARAMETER_MISSING);
//        }
        if (null == model.getOs()) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        String iosNoIdfa = "00000000-0000-0000-0000-000000000000";
        if (iosNoIdfa.equals(model.getImei())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        String ios = "1";
        String imei = model.getOs().equals(ios) ? model.getImei() : SecurityUtil.md5(model.getImei()).toLowerCase();
        TouTiaoAdvMongo touTiaoAdvMongo = touTiaoAdvMongoDao.getByImeiAndOsAndStatus(imei, model.getOs(), "00");
        if (touTiaoAdvMongo == null) {
            throw new ApiException(ResultStatus.DATA_QUERY_FAILED);
        }
        String convTime = String.valueOf(System.currentTimeMillis());

        touTiaoAdvMongo.setStatus("01");
        touTiaoAdvMongoDao.update(touTiaoAdvMongo);

        //回传
        String url = "http://ad.toutiao.com/track/activate/?callback=" + URLEncoder.encode(touTiaoAdvMongo.getCallback(), "UTF-8") + "&muid=" + touTiaoAdvMongo.getImei() + touTiaoAdvMongo.getMac() + "&os=" + touTiaoAdvMongo.getOs() + "&source=TD&conv_time=" + convTime + "&event_type=0";
        HttpUtil.get(url);
    }

    @Override
    public void platformTracking(Customer customer, PlatformTrackingRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PlatformTrackingMongo platformTrackingMongo = new PlatformTrackingMongo();
        platformTrackingMongo.setCustomerId(customer.getId());
        platformTrackingMongo.setCreateTime(new Date());
        platformTrackingMongo.setImie(model.getImie());
        platformTrackingMongo.setMac(model.getMac());
        platformTrackingMongo.setPlatform(model.getPlatform());
        platformTrackingMongo.setVersion(model.getVersion());
        platformTrackingMongoDao.add(platformTrackingMongo);
    }

    @Override
    public ShareInfoViewModel shareInfo(Customer customer, ShareInfoRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getName())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        ShareInfoViewModel viewModel = new ShareInfoViewModel();
        String shareName = model.getName();
        if (shareName.contains(Constants.BACKSLASH)) {
            // 去掉请求时多带过来的"/"
            shareName = shareName.replaceAll(Constants.BACKSLASH, Constants.EMPTY_STRING);
        }
        ShareContentMongo shareContent = shareContentMongoDao.getByType(shareName);
        if (null == shareContent) {
            throw new ApiException(ResultStatus.DATA_QUERY_FAILED);
        }
        if (shareContent.getMustLogin()) {
            if (null == customer) {
                throw new ApiException(ResultStatus.PARAMETER_MISSING);
            }
        }
        viewModel.setType(shareContent.getType());
        viewModel.setShareTo(shareContent.getShareTo());
        String shareLink;
        String platform = model.getPlatform();
        String domain = Constants.EMPTY_STRING;
        if (Platform.IOS.getName().equals(platform)) {
            domain = Domain.IOS.getDomain();
        } else if (Platform.ANDROID.getName().equals(platform)) {
            domain = Domain.ANDROID.getDomain();
        } else if (Platform.WEIXIN.getName().equals(platform)) {
            domain = Domain.WEIXIN.getDomain();
        } else if (Platform.WAP.getName().equals(platform)) {
            domain = Domain.WAP.getDomain();
        }
        shareLink = shareContent.getShareSuccessLink().replace(Constants.DOMAIN_PLACEHOLDER, domain);
        viewModel.setShareSuccessLink(shareLink);
        shareContent.setShareSuccessType(shareContent.getShareSuccessType());
        viewModel.setShareSuccessType(shareContent.getShareSuccessType());
        viewModel.setShareSuccessMessage(shareContent.getShareSuccessMessage());
        viewModel.setShareIcon(shareContent.getIcon());
        if (shareContent.getMustLogin()) {
            String name = customer.getName();
            if (StringUtil.isNullOrEmpty(name)) {
                // 优先使用真实姓名，为空则使用昵称
                name = customer.getNickName();
            }
            viewModel.setShareTitle(shareContent.getTitle().replace(Constants.TITLE_PLACEHOLDER, name));
            String url = shareContent.getUrl().replace(Constants.SIGN_PLACEHOLDER, customer.getSign()).replace(Constants.DOMAIN_PLACEHOLDER, domain);
            viewModel.setShareUrl(url);
            if (customer.getPhoto().toLowerCase().startsWith(Constants.HTTP)) {
                viewModel.setShareIcon(customer.getPhoto());
            } else {
                viewModel.setShareIcon(Domain.WEIXIN.getDomain() + customer.getPhoto());
            }
        }
        viewModel.setShareContent(shareContent.getContent());
        if (StringUtil.isNullOrEmpty(model.getImageUrl())) {
            viewModel.setImg(shareContent.getImg());
        } else {
            viewModel.setImg(model.getImageUrl());
        }

        return viewModel;
    }
}
