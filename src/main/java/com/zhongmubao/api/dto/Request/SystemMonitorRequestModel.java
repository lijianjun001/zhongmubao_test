package com.zhongmubao.api.dto.Request;

import com.zhongmubao.api.config.enmu.Platform;

/**
 * 牧场监控请求参数
 */
public class SystemMonitorRequestModel {
    /**
     * SheepProjectId
     */
    private int id;
    /**
     * 平台类型
     */
    private Platform platform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
