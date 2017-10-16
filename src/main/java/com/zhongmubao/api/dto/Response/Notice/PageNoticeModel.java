package com.zhongmubao.api.dto.Response.Notice;

import java.util.List;

public class PageNoticeModel {

    public PageNoticeModel() {
    }

    public PageNoticeModel(int pageCount, List<PageNoticeViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
    }

    private int pageCount;
    private List<PageNoticeViewModel> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<PageNoticeViewModel> getList() {
        return list;
    }

    public void setList(List<PageNoticeViewModel> list) {
        this.list = list;
    }
}
