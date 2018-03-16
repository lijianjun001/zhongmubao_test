package com.zhongmubao.api.dto.request.my.redpackage;

import com.zhongmubao.api.config.enmu.RedPackageGroupType;
import com.zhongmubao.api.config.enmu.RedPackageSortType;
import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 红包列表请求
 *
 * @author 孙阿龙
 */
public class ListRequestModel extends BaseRequest {
    /**
     * 当前页码
     */
    private int pageIndex;

    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 红包类型
     */
    private RedPackageGroupType groupType;

    /**
     * 排序方式
     */
    private RedPackageSortType sortType;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public RedPackageGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(RedPackageGroupType groupType) {
        this.groupType = groupType;
    }

    public RedPackageSortType getSortType() {
        return sortType;
    }

    public void setSortType(RedPackageSortType sortType) {
        this.sortType = sortType;
    }
}
