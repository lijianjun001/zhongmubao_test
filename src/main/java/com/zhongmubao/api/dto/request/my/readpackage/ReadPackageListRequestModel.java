package com.zhongmubao.api.dto.request.my.readpackage;

import com.zhongmubao.api.config.enmu.RedPackageGroupType;
import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 红包列表请求
 *
 * @author 孙阿龙
 */
public class ReadPackageListRequestModel extends BaseRequest {
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

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public RedPackageGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(RedPackageGroupType groupType) {
        this.groupType = groupType;
    }
}
