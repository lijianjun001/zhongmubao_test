package com.zhongmubao.api.dto.response.system;

import java.util.List;

/**
 * PageSystemServerActionViewModel
 *
 * @author 孙阿龙
 */
public class PageSystemServerActionViewModel {
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<SystemServerActionModel> getList() {
        return list;
    }

    public void setList(List<SystemServerActionModel> list) {
        this.list = list;
    }

    private List<SystemServerActionModel> list;
}
