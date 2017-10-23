package com.zhongmubao.api.dto.request;


/**
 * 此请求类仅用于接收只传一个主键
 * 如：删除、GetById()等操作
 * @author 米立林
 */
public class OnlyPrimaryIdRequestModel {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
