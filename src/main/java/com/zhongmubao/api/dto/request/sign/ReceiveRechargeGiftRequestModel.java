package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * ReceiveRechargeGiftRequestModel
 *
 * @author 孙阿龙
 */
public class ReceiveRechargeGiftRequestModel extends BaseRequest {
    private String giftId;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }
}
