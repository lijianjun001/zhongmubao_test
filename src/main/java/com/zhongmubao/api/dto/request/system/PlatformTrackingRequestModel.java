package com.zhongmubao.api.dto.request.system;

/**
 * 平台跟踪请求model
 *
 * @author 孙阿龙
 */
public class PlatformTrackingRequestModel {

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    private String platform;
    private String imie;
    private String mac;

}
