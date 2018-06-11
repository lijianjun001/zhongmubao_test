package com.zhongmubao.api.dto.response.mp.share;

/***
 * 分享发起
 * @author 孙阿龙
 */
public class LaunchViewModel {
    private String status;
    public String redEnvelope;

    public String getStatus() {
        return status;
    }

    public String getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(String redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
