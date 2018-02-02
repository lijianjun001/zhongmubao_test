package com.zhongmubao.api.dto.response.system;

import java.util.List;

/**
 * PageSystemServerActionModel
 *
 * @author 孙阿龙
 */
public class PageSystemServerActionModel {
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<SystemServerActionViewModel> getList() {
        return list;
    }

    public void setList(List<SystemServerActionViewModel> list) {
        this.list = list;
    }

    private List<SystemServerActionViewModel> list;
}
