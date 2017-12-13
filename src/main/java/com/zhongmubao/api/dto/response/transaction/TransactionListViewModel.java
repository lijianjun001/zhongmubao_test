package com.zhongmubao.api.dto.response.transaction;

import java.util.ArrayList;

/**
 * 交易明细列表响应实体
 * @author 米立林
 */
public class TransactionListViewModel {
    public TransactionListViewModel() {
    }

    public TransactionListViewModel(int totalPage, ArrayList<TransactionViewModel> list) {
        this.totalPage = totalPage;
        this.list = list;
    }

    /**
     * 总页码
     */
    private int totalPage;

    /**
     * 红包集合
     */
    private ArrayList<TransactionViewModel> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList<TransactionViewModel> getList() {
        return list;
    }

    public void setList(ArrayList<TransactionViewModel> list) {
        this.list = list;
    }
}
