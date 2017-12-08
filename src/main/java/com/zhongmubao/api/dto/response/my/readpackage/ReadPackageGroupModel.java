package com.zhongmubao.api.dto.response.my.readpackage;

import com.zhongmubao.api.config.enmu.RedPackageGroupType;

import java.util.ArrayList;

/**
 * 我的红包分组列表Model
 *
 * @author 孙阿龙
 */
public class ReadPackageGroupModel {

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
     * 红包类型
     */
    private RedPackageGroupType groupType;

    /**
     * 预加载数据页码
     */
    private int preLoadPageIndex;

    /**
     * 预加载数据
     */
    private ArrayList<ReadPackageModel> preLoadList;

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

    public ArrayList<ReadPackageModel> getPreLoadList() {
        return preLoadList;
    }

    public void setPreLoadList(ArrayList<ReadPackageModel> preLoadList) {
        this.preLoadList = preLoadList;
    }

    public int getPreLoadPageIndex() {
        return preLoadPageIndex;
    }

    public void setPreLoadPageIndex(int preLoadPageIndex) {
        this.preLoadPageIndex = preLoadPageIndex;
    }

    public RedPackageGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(RedPackageGroupType groupType) {
        this.groupType = groupType;
    }
}
