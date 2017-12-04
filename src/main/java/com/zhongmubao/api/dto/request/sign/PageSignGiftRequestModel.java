package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * PageSignGiftRequestModel
 *
 * @author 孙阿龙
 */
public class PageSignGiftRequestModel extends BaseRequest {
    public PageSignGiftRequestModel(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public PageSignGiftRequestModel() {
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    private int pageIndex;
}
