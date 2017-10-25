package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.dto.request.system.PlatformTrackingRequestModel;
import com.zhongmubao.api.dto.request.system.TouTiaoAdvRequestModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.PlatformTrackingMongoDao;
import com.zhongmubao.api.mongo.dao.TouTiaoAdvMongoDao;
import com.zhongmubao.api.mongo.entity.PlatformTrackingMongo;
import com.zhongmubao.api.mongo.entity.TouTiaoAdvMongo;
import com.zhongmubao.api.service.BaseService;
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

    @Autowired
    public SystemServiceImpl(TouTiaoAdvMongoDao touTiaoAdvMongoDao, PlatformTrackingMongoDao platformTrackingMongoDao) {
        this.touTiaoAdvMongoDao = touTiaoAdvMongoDao;
        this.platformTrackingMongoDao = platformTrackingMongoDao;
    }

    @Override
    public void touTiaoAdv(TouTiaoAdvRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (null == model.getMac()) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (null == model.getImei()) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (null == model.getOs()) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        String imei = SecurityUtil.md5(model.getImei()).toLowerCase();
        TouTiaoAdvMongo touTiaoAdvMongo = touTiaoAdvMongoDao.getByImeiAndOsAndStatus(imei, model.getOs(), "00");
        if (touTiaoAdvMongo == null) {
            throw new ApiException(ResultStatus.DATA_QUERY_FAILED);
        }
        String convTime = String.valueOf(System.currentTimeMillis());
        //回传
        String url = "http://ad.toutiao.com/track/activate/?callback=" + URLEncoder.encode(touTiaoAdvMongo.getCallback(), "UTF-8") + "&muid=" + touTiaoAdvMongo.getImei() + touTiaoAdvMongo.getMac() + "&os=" + touTiaoAdvMongo.getOs() + "&source=TD&conv_time=" + convTime + "&event_type=0";
        HttpUtil.get(url);
        touTiaoAdvMongoDao.update(touTiaoAdvMongo);
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

        platformTrackingMongoDao.add(platformTrackingMongo);
    }
}
