package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.entity.Customer;

/**
 * 系统服务
 *
 * @author 孙阿龙
 */
public interface SystemService {

    /**
     * 头条广告
     * @author xy
     * @param model 请求model
     * @throws Exception 错误信息
     */
    void touTiaoAdv(TouTiaoAdvRequestModel model) throws Exception;

    /**
     * 平台跟踪
     *
     * @param customer 客户
     * @param model    请求model
     * @throws Exception 异常
     * @author 孙阿龙
     */
    void platformTracking(Customer customer, PlatformTrackingRequestModel model) throws Exception;
}
