package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.touTiaoAdv.TouTiaoAdvRequestModel;

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
}
