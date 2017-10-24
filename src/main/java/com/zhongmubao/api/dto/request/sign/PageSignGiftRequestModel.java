package com.zhongmubao.api.dto.request.sign;

/**
 * PageSignGiftRequestModel
 *
 * @author 孙阿龙
 */
public class PageSignGiftRequestModel {
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
