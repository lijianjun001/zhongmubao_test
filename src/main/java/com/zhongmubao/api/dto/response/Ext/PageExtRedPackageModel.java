package com.zhongmubao.api.dto.response.Ext;

import java.util.List;

public class PageExtRedPackageModel {

    public PageExtRedPackageModel(int pageCount, List<PageExtRedPackageViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
    }

    private int pageCount;
    private List<PageExtRedPackageViewModel> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<PageExtRedPackageViewModel> getList() {
        return list;
    }

    public void setList(List<PageExtRedPackageViewModel> list) {
        this.list = list;
    }
}
