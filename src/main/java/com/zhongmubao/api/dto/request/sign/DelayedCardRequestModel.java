package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * DelayedCardRequestModel
 *
 * @author 孙阿龙
 */
public class DelayedCardRequestModel extends BaseRequest {
    public DelayedCardRequestModel() {
    }

    public DelayedCardRequestModel(int packageId) {
        this.packageId = packageId;
    }

    private int packageId;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
