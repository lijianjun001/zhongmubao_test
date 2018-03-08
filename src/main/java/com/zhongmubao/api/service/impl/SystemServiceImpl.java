package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.config.enmu.SmsType;
import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.system.ShareInfoViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.*;
import com.zhongmubao.api.mongo.entity.PlatformTrackingMongo;
import com.zhongmubao.api.mongo.entity.ShareContentMongo;
import com.zhongmubao.api.mongo.entity.SystemSmsLogMongo;
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
    private final SystemSmsLogMongoDao systemSmsLogMongoDao;

    @Autowired
    public SystemServiceImpl(TouTiaoAdvMongoDao touTiaoAdvMongoDao, PlatformTrackingMongoDao platformTrackingMongoDao, SystemServerActionMongoDao systemServerActionMongoDao, ShareContentMongoDao shareContentMongoDao, SystemSmsLogMongoDao systemSmsLogMongoDao) {
        this.touTiaoAdvMongoDao = touTiaoAdvMongoDao;
        this.platformTrackingMongoDao = platformTrackingMongoDao;
        this.systemServerActionMongoDao = systemServerActionMongoDao;
        this.shareContentMongoDao = shareContentMongoDao;
        this.systemSmsLogMongoDao = systemSmsLogMongoDao;
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
        //region 验证数据
        if (null == model || StringUtil.isNullOrEmpty(model.getName())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        //endregion

        /* replace处理掉多余的反斜杠 */
        String shareName = model.getName().replace(Constants.BACKSLASH, Constants.EMPTY_STRING).toLowerCase();
        ShareContentMongo shareContent = shareContentMongoDao.getByType(shareName);
        if (null == shareContent) {
            throw new ApiException(ResultStatus.DATA_QUERY_FAILED);
        }

        String domain = ApiUtil.getDomainByPlatform(model.getPlatform());
        String name = Constants.EMPTY_STRING;
        String sign = Constants.EMPTY_STRING;
        String photo = shareContent.getIcon();

        if (shareContent.getMustLogin()) {
            if (null == customer) {
                throw new ApiException(ResultStatus.PARAMETER_MISSING);
            }
            name = customer.getName();
            name = StringUtil.isNullOrEmpty(name) ? customer.getNickName() : name;
            sign = customer.getSign();
            photo = customer.getPhoto();
            photo = StringUtil.isNullOrEmpty(photo) ? Constants.DEFAULT_PHOTO : photo.toLowerCase().startsWith(Constants.HTTP) ? photo : Domain.WEIXIN.getDomain() + photo;
        }

        String shareLink = shareContent.getShareSuccessLink().replace(Constants.DOMAIN_PLACEHOLDER, domain);
        String url = shareContent.getUrl().replace(Constants.SIGN_PLACEHOLDER, sign).replace(Constants.DOMAIN_PLACEHOLDER, domain);
        String title = shareContent.getTitle().replace(Constants.TITLE_PLACEHOLDER, name);
        String imageUrl = SerializeUtil.getJsonStringValueByKey(model.getParam(), "imgUrl");
        imageUrl = StringUtil.isNullOrEmpty(imageUrl) ? shareContent.getImg() : imageUrl;

        // 处理每日分享的图片
        String shareday = "shareday";
        if (shareday.equals(shareName)) {
            try {
                String res = HttpUtil.get(Constants.SHARE_IMG_URL + "?data=" + ApiUtil.inviteCode(customer.getId()));
                imageUrl = SerializeUtil.getJsonStringValueByKey(res, "data");
            } catch (Exception ex) {

            }
        }

        ShareInfoViewModel viewModel = new ShareInfoViewModel();
        viewModel.setType(shareContent.getType());
        viewModel.setShareTo(shareContent.getShareTo());
        viewModel.setShareSuccessLink(shareLink);
        viewModel.setShareSuccessType(shareContent.getShareSuccessType());
        viewModel.setShareSuccessMessage(shareContent.getShareSuccessMessage());
        viewModel.setShareIcon(shareContent.getIcon());
        viewModel.setShareTitle(title);
        viewModel.setShareUrl(url);
        viewModel.setShareIcon(photo);
        viewModel.setShareContent(shareContent.getContent());
        viewModel.setImg(imageUrl);

        return viewModel;
    }

    @Override
    public void sendSms(Customer customer, SendSmsRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getPhone())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (!RegExpMatcher.matcherMobile(model.getPhone())) {
            throw new ApiException(ResultStatus.INVALID_PHONE_ERROR);
        }
        Date now = new Date();
        String title = "验证短信校验码";
        int code = MathUtil.random(100000, 999999);
        String content = "验证码:" + code + ",有效期为30分钟，为保证您的账户安全，请勿将此验证短信转发他人.【众牧宝】";
        sendSms(model.getPhone(), title, content);
        // 保存记录
        SystemSmsLogMongo smsLog = new SystemSmsLogMongo();
        smsLog.setType(SmsType.VERIFICATION.getName());
        smsLog.setPhone(model.getPhone());
        smsLog.setCode(code);
        smsLog.setMessage(content);
        smsLog.setExpired(DateUtil.formatMongo(DateUtil.addMinute(now, 30)));
        smsLog.setCreated(DateUtil.formatMongo(now));
        smsLog.setAsyncType(0);
        systemSmsLogMongoDao.save(smsLog);

    }

    //    @Override
//    @Transactional(timeout=1000, isolation= Isolation.DEFAULT, propagation= Propagation.REQUIRED,rollbackFor = ApiException.class)
//    public void testTransaction() {
//        CustomerShare share = new CustomerShare();
//        share.setPrice(0.1);
//        share.setCustomerId(4194);
//        share.setCreateTime(new Date());
//        share.setPlatform("00");
//        customerShareDao.insertCustomerShare(share);
//
//        int id = share.getId();
//        System.out.println(id);
//        //异常抛出后事务就会回滚
//        throw new ApiException("sss");
//    }
}
