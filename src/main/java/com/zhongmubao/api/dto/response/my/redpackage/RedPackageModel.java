package com.zhongmubao.api.dto.response.my.redpackage;

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
    private String typeStr;

    /**
     * 是否新
     */
    private boolean whetherNew;

    /**
     * 有效期
     */
    private String expTime;

    /**
     * 是否更早期红包
     */
    private boolean whetherEarlier;

    /**
     * 状态(00未使用，01已使用，02已过期)
     */
    private String status;

    public RedPackageModel(int id, String price, String remark, String typeStr, boolean whetherNew, String expTime, boolean whetherEarlier, String status) {
        this.id = id;
        this.price = price;
        this.remark = remark;
        this.typeStr = typeStr;
        this.whetherNew = whetherNew;
        this.expTime = expTime;
        this.whetherEarlier = whetherEarlier;
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

    public boolean isWhetherNew() {
        return whetherNew;
    }

    public void setWhetherNew(boolean whetherNew) {
        this.whetherNew = whetherNew;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public boolean isWhetherEarlier() {
        return whetherEarlier;
    }

    public void setWhetherEarlier(boolean whetherEarlier) {
        this.whetherEarlier = whetherEarlier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
