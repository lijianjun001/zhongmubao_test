package com.zhongmubao.api.dto.response.my.redpackage;

import com.zhongmubao.api.config.enmu.RedPackageState;

import java.util.ArrayList;

/**
 * 红包详情
 *
 * @author 孙阿龙
 */
public class DetailViewModel {

    public DetailViewModel() {
    }

    public DetailViewModel(String type, String typeStr, String price, String beginTime, String expTime, ArrayList<String> remarks, String status) {
        this.type = type;
        this.typeStr = typeStr;
        this.price = price;
        this.beginTime = beginTime;
        this.expTime = expTime;
        this.remarks = remarks;
        this.status = status;
    }

    /**
     * 红包类型
     */
    private String type;

    /**
     * 红包类型中文
     */
    private String typeStr;

    /**
     * 金额
     */
    private String price;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 过期时间
     */
    private String expTime;

    /**
     * 备注集合
     */
    private ArrayList<String> remarks;

    /**
     * 状态(00未使用，01已使用，02已过期)
     */
    private String status;

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public ArrayList<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(ArrayList<String> remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
