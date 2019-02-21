package com.zhongmubao.api.service.impl.buySheep;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/28.
 */

public class BonusModel {
    private String TotalAmount;
    private ArrayList<String> RedPackageList;
    private String MaxSelectedCount;

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public ArrayList<String> getRedPackageList() {
        return RedPackageList;
    }

    public void setRedPackageList(ArrayList<String> redPackageList) {
        RedPackageList = redPackageList;
    }

    public String getMaxSelectedCount() {
        return MaxSelectedCount;
    }

    public void setMaxSelectedCount(String maxSelectedCount) {
        MaxSelectedCount = maxSelectedCount;
    }
}
