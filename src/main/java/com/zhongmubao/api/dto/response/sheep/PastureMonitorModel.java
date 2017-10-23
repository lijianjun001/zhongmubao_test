package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

/**
 * 牧场监控
 */
public class PastureMonitorModel {
    /**
     * 监控视频url
     */
    private String videoUrl;
    /**
     * 羊耳标
     */
    private List<PastureSheepErBiaoModel> list;

    public PastureMonitorModel(String videoUrl, List<PastureSheepErBiaoModel> list) {
        this.videoUrl = videoUrl;
        this.list = list;
    }

    public PastureMonitorModel() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<PastureSheepErBiaoModel> getList() {
        return list;
    }

    public void setList(List<PastureSheepErBiaoModel> list) {
        this.list = list;
    }
}
