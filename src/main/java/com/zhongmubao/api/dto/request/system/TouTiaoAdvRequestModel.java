package com.zhongmubao.api.dto.request.system;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * TouTiaoAdvRequestModel
 *
 * @author xy
 */
public class TouTiaoAdvRequestModel extends BaseRequest {

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    private String mac;
    private String imei;
    private String ip;
    private String os;
    private String deviceid;

}
