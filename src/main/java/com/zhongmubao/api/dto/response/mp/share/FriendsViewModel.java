package com.zhongmubao.api.dto.response.mp.share;

import java.util.ArrayList;

/**
 * 红包进度ViewModel
 *
 * @author 米立林
 */
public class FriendsViewModel {
    /**
     * 状态 00进行中 01完成 02失败 03不能参与 04红包已打开
     */
    private String status;
    private int surplus;
    private String totalPrice;
    private int countdown;
    private ArrayList<RedEnvelopeCustomer> list;
    private boolean my;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public ArrayList<RedEnvelopeCustomer> getList() {
        return list;
    }

    public void setList(ArrayList<RedEnvelopeCustomer> list) {
        this.list = list;
    }

    public boolean isMy() {
        return my;
    }

    public void setMy(boolean my) {
        this.my = my;
    }
}
