package com.zhongmubao.api.dto.request.system;

/**
 * 分页请求参数
 *
 * @author 米立林
 */
public class PageRequestModel {

    /**
     * 当前页码
     */
    private int pageIndex;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
