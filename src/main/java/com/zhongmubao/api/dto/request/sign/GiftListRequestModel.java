package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * GiftListRequestModel
 *
 * @author 孙阿龙
 */
public class GiftListRequestModel extends BaseRequest {
    private int pageIndex;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
