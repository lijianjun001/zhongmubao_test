package com.zhongmubao.api.dto.request;

import com.zhongmubao.api.config.enmu.RedPackageSortType;

/**
 * @author 米立林
 */
public class PageExtRedPackageRequestModel {

    /**
     * 页码
     */
    private int pageIndex;

    /**
     * 排序类型：Price or ExpTime
     */
    private RedPackageSortType sortType;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public RedPackageSortType getSortType() {
        return sortType;
    }

    public void setSortType(RedPackageSortType sortType) {
        this.sortType = sortType;
    }
}
