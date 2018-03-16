package com.zhongmubao.api.dto.request.my.redpackage;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 红包详情请求
 *
 * @author 孙阿龙
 */
public class DetailRequestModel extends BaseRequest {

    /**
     * 红包id
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
