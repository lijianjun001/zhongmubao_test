package com.zhongmubao.api.dto.response.my.redpackage;

import java.util.ArrayList;

/**
 * 我的红包分组列表Model
 *
 * @author 孙阿龙
 */
public class GroupModel {

    /**
     * 红包金额
     */
    private String price;

    /**
     * 总共金额
     */
    private String totalPrice;

    /**
     * 红包数量
     */
    private int count;

    /**
     * 新到红包数量
     */
    private int newCount;

    /**
     * 最早一个红包到期的时间
     */
    private String firstExpTime;

    /**
     * 红包类型
     */
    private String type;

    /**
     * 红包组类型
     */
    private String groupType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单个红包
     */
    private RedPackageModel redPackageModel;

    /**
     * 预加载数据页码
     */
    private int preLoadTotalPage;

    /**
     * 预加载数据
     */
    private ArrayList<RedPackageModel> preLoadList;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public String getFirstExpTime() {
        return firstExpTime;
    }

    public void setFirstExpTime(String firstExpTime) {
        this.firstExpTime = firstExpTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<RedPackageModel> getPreLoadList() {
        return preLoadList;
    }

    public void setPreLoadList(ArrayList<RedPackageModel> preLoadList) {
        this.preLoadList = preLoadList;
    }

    public int getPreLoadTotalPage() {
        return preLoadTotalPage;
    }

    public void setPreLoadTotalPage(int preLoadTotalPage) {
        this.preLoadTotalPage = preLoadTotalPage;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RedPackageModel getRedPackageModel() {
        return redPackageModel;
    }

    public void setRedPackageModel(RedPackageModel redPackageModel) {
        this.redPackageModel = redPackageModel;
    }
}
