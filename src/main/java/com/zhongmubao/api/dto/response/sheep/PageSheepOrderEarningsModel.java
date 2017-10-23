package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

public class PageSheepOrderEarningsModel {
    public PageSheepOrderEarningsModel() {
    }

    public PageSheepOrderEarningsModel(int pageCount, List<PageOrderEarningsViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
    }

    private int pageCount;
    private List<PageOrderEarningsViewModel> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<PageOrderEarningsViewModel> getList() {
        return list;
    }

    public void setList(List<PageOrderEarningsViewModel> list) {
        this.list = list;
    }
}
