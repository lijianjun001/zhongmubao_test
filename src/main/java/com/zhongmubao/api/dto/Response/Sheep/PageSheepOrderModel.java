package com.zhongmubao.api.dto.Response.Sheep;

import java.util.List;

/**
 * 获取所有的买羊订单
 */
public class PageSheepOrderModel {
    public PageSheepOrderModel() {
    }

    public PageSheepOrderModel(int pageCount, List<PageSheepOrderViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
    }

    private int pageCount;
    private List<PageSheepOrderViewModel> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<PageSheepOrderViewModel> getList() {
        return list;
    }

    public void setList(List<PageSheepOrderViewModel> list) {
        this.list = list;
    }
}
