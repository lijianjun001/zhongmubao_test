package com.zhongmubao.api.dto.response.my.readpackage;

import com.zhongmubao.api.config.enmu.RedPackageState;

import java.util.ArrayList;

/**
 * 红包详情
 *
 * @author 孙阿龙
 */
public class ReadPackageDetailViewModel {

    public ReadPackageDetailViewModel() {
    }

    public ReadPackageDetailViewModel(String typeStr, String price, String beginTime, String expTime, ArrayList<String> remarks, RedPackageState status) {
        this.typeStr = typeStr;
        this.price = price;
        this.beginTime = beginTime;
        this.expTime = expTime;
        this.remarks = remarks;
        this.status = status;
    }

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
    private RedPackageState status;

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

    public RedPackageState getStatus() {
        return status;
    }

    public void setStatus(RedPackageState status) {
        this.status = status;
    }


}
