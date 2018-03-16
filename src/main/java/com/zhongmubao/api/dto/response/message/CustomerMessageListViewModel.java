package com.zhongmubao.api.dto.response.message;

import java.util.ArrayList;

/**
 * 消息列表
 *
 * @author 米立林
 */
public class CustomerMessageListViewModel {
    private int totalPage;
    private ArrayList<CustomerMessageModel> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList<CustomerMessageModel> getList() {
        return list;
    }

    public void setList(ArrayList<CustomerMessageModel> list) {
        this.list = list;
    }
}
