package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * ReceiveRechargeGiftRequestModel
 *
 * @author 孙阿龙
 */
public class PageSignPackageRequestModel extends BaseRequest {

    private int pageIndex;
    private String type;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
