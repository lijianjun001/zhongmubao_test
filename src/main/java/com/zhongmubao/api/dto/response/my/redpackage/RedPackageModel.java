package com.zhongmubao.api.dto.response.my.redpackage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 我的红包列表Model
 *
 * @author 孙阿龙
 */
public class RedPackageModel {

    /**
     * 唯一标识
     */
    private int id;

    /**
     * 红包金额
     */
    private String price;

    /**
     * 红包备注
     */
    private String remark;

    /**
     * 红包类型中文
     */
    private String type;

    /**
     * 红包类型中文
     */
    private String typeStr;

    /**
     * 是否新
     */
    private boolean isNew;

    /**
     * 有效期
     */
    private String expTime;

    /**
     * 状态(00未使用，01已使用，02已过期)
     */
    private String status;

    public RedPackageModel(int id, String price, String remark, String type, String typeStr, boolean isNew, String expTime, String status) {
        this.id = id;
        this.price = price;
        this.remark = remark;
        this.type = type;
        this.typeStr = typeStr;
        this.isNew = isNew;
        this.expTime = expTime;
        this.status = status;
    }

    public RedPackageModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty(value = "isNew")
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

}
