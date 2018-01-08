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

    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 排序方式
     */
    private RedPackageSortType sortType;

    public RedPackageSortType getSortType() {
        return sortType;
    }

    public void setSortType(RedPackageSortType sortType) {
        this.sortType = sortType;
    }

    /**
     * 是否获取更早历史红包
     */
    private boolean loadEarlier;

    public boolean isLoadEarlier() {
        return loadEarlier;
    }

    public void setLoadEarlier(boolean loadEarlier) {
        this.loadEarlier = loadEarlier;
    }
}
