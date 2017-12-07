package com.zhongmubao.api.dto.request.my.readpackage;

import com.zhongmubao.api.config.enmu.RedPackageSortType;
import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 红包分组请求model
 *
 * @author 孙阿龙
 */
public class ReadPackageGroupRequestModel extends BaseRequest {
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

}
