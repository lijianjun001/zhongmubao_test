package com.zhongmubao.api.dto.request.system;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * SystemServerAction分页
 *
 * @author 孙阿龙
 */
public class SystemServerActionPagerRequestModel extends BaseRequest {

    public String name;
    private int pageIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


}
