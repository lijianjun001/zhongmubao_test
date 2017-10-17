package com.zhongmubao.api.dto.Response.Sheep;

/**
 * 牧场监控
 */
public class PastureMonitorModel {
    private String videoUrl;

    public PastureMonitorModel(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public PastureMonitorModel() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
