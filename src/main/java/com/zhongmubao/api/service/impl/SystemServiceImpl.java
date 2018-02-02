package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.system.PageSystemServerActionViewModel;
import com.zhongmubao.api.dto.response.system.SystemServerActionListViewModel;
import com.zhongmubao.api.dto.response.system.SystemServerActionModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.PlatformTrackingMongoDao;
import com.zhongmubao.api.mongo.dao.SystemServerActionMongoDao;
import com.zhongmubao.api.mongo.dao.TouTiaoAdvMongoDao;
import com.zhongmubao.api.mongo.entity.PlatformTrackingMongo;
import com.zhongmubao.api.mongo.entity.SystemServerActionMongo;
import com.zhongmubao.api.mongo.entity.TouTiaoAdvMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.SystemService;
import com.zhongmubao.api.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    public SystemServiceImpl(TouTiaoAdvMongoDao touTiaoAdvMongoDao, PlatformTrackingMongoDao platformTrackingMongoDao, SystemServerActionMongoDao systemServerActionMongoDao) {
        this.touTiaoAdvMongoDao = touTiaoAdvMongoDao;
        this.platformTrackingMongoDao = platformTrackingMongoDao;
        this.systemServerActionMongoDao = systemServerActionMongoDao;
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
    public void saveServerAction(SystemServerActionSaveRequestModel model) throws Exception {
        if (StringUtil.isNullOrEmpty(model.getName())) {
            throw new ApiException("名称不能为空");
        }

        if (StringUtil.isNullOrEmpty(model.getObjectId())) {
            SystemServerActionMongo systemServerActionMongo = new SystemServerActionMongo();
            systemServerActionMongo.setName(model.getName());
            systemServerActionMongo.setChinaName(model.getChinaName());
            systemServerActionMongo.setParentObjectId(model.getParentObjectId());
            systemServerActionMongoDao.add(systemServerActionMongo);
        } else {
            SystemServerActionMongo systemServerActionMongo = systemServerActionMongoDao.get(model.getObjectId());
            if (systemServerActionMongo == null) {
                throw new ApiException("数据不存在");
            }
            systemServerActionMongo.setName(model.getName());
            systemServerActionMongo.setChinaName(model.getChinaName());
            if (!StringUtil.isNullOrEmpty(model.getParentObjectId())) {
                systemServerActionMongo.setName(model.getParentObjectId());
            }
            systemServerActionMongoDao.save(systemServerActionMongo);
        }
    }

    @Override
    public PageSystemServerActionViewModel pagerServerAction(SystemServerActionPagerRequestModel model) throws Exception {
        PageSystemServerActionViewModel resultModel = new PageSystemServerActionViewModel();

        PageModel<SystemServerActionMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager = systemServerActionMongoDao.pager(model.getName(), false, pager);

        List<SystemServerActionModel> list = new ArrayList<>();
        for (SystemServerActionMongo systemServerActionMongo : pager.getDatas()) {
            list.add(formartSystemServerActionModel(systemServerActionMongo));
        }
        resultModel.setPageCount(pager.getTotalPages());
        resultModel.setList(list);
        return resultModel;
    }

    @Override
    public SystemServerActionListViewModel serverActionList(SystemServerActionListRequestModel model) throws Exception {
        SystemServerActionListViewModel result = new SystemServerActionListViewModel();

        List<SystemServerActionMongo> systemServerActionMongoList = systemServerActionMongoDao.getListByParentObjectId(model.getParentObjectId());

        List<SystemServerActionModel> list = new ArrayList<>();
        for (SystemServerActionMongo systemServerActionMongo : systemServerActionMongoList) {
            list.add(formartSystemServerActionModel(systemServerActionMongo));
        }
        result.setList(list);
        return result;
    }

    @Override
    public void DelSystemServerAction(SystemServerActionDelRequestModel model) throws Exception {
        SystemServerActionMongo systemServerActionMongo = systemServerActionMongoDao.get(model.getObjectId());
        if (systemServerActionMongo == null) {
            throw new Exception("数据不存在");
        }
        systemServerActionMongoDao.delete(systemServerActionMongo);
    }

    private SystemServerActionModel formartSystemServerActionModel(SystemServerActionMongo systemServerActionMongo) throws Exception {
        SystemServerActionModel viewModel = new SystemServerActionModel();
        viewModel.setServer(systemServerActionMongo.getName());
        if (!StringUtil.isNullOrEmpty(systemServerActionMongo.getParentObjectId())) {
            SystemServerActionMongo systemServerActionMongo1 = systemServerActionMongoDao.get(systemServerActionMongo.getParentObjectId());
            viewModel.setAction(systemServerActionMongo1 != null ? systemServerActionMongo1.getName() : "");
        }

        viewModel.setObjectId(systemServerActionMongo.id);
        return viewModel;
    }
}
