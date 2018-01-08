package com.zhongmubao.api.dto.request.my.redpackage;

import com.zhongmubao.api.config.enmu.RedPackageSortType;

/**
 * 历史红包请求参数
 *
 * @author 米立林
 */
public class RedPackageHistoryRequestModel {
    /**
     * 当前页码
     */
    private int pageIndex;

    /**
     * 排序方式
     */
    private RedPackageSortType sortType;

    /**
     * 获取更早历史红包
     */
    private boolean loadEarlier;


    public int getPageIndex() {
        return pageIndex;
    }

    public RedPackageSortType getSortType() {
        return sortType;
    }

    public void setSortType(RedPackageSortType sortType) {
        this.sortType = sortType;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public boolean isLoadEarlier() {
        return loadEarlier;
    }

    public void setLoadEarlier(boolean loadEarlier) {
        this.loadEarlier = loadEarlier;
    }

}
