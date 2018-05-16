package com.zhongmubao.api.dto.response.my;

/**
 * 我的牧场
 *
 * @author 米立林
 */
public class MyPastureViewModel {
    private int inbarCount;
    private String firstRedeemDate;
    private String finallyRedeemDate;

    public int getInbarCount() {
        return inbarCount;
    }

    public void setInbarCount(int inbarCount) {
        this.inbarCount = inbarCount;
    }

    public String getFirstRedeemDate() {
        return firstRedeemDate;
    }

    public void setFirstRedeemDate(String firstRedeemDate) {
        this.firstRedeemDate = firstRedeemDate;
    }

    public String getFinallyRedeemDate() {
        return finallyRedeemDate;
    }

    public void setFinallyRedeemDate(String finallyRedeemDate) {
        this.finallyRedeemDate = finallyRedeemDate;
    }
}
