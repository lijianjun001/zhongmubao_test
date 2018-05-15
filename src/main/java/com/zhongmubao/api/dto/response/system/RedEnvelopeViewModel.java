package com.zhongmubao.api.dto.response.system;

import java.util.ArrayList;

/**
 * 小程序红包响应类
 *
 * @author Yekai
 */
public class RedEnvelopeViewModel {
    /**
     * 状态 00进行中 01完成 02失败
     */
    private String status;
    private String text;
    private String totalPrice;
    private int countdown;
    private ArrayList<RedEnvelopeCustomer> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
