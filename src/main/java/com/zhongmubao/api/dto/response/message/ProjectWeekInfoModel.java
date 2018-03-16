package com.zhongmubao.api.dto.response.message;

import java.util.List;

/**
 * 开标周期信息
 *
 * @author 米立林
 */
public class ProjectWeekInfoModel {
    /**
     * 周几
     */
    private String week;

    /**
     * "00"已售罄 "01"未售罄
     */
    private String sellout;

    /**
     * 开标计划列表
     */
    private List<ProjectDetailModel> list;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSellout() {
        return sellout;
    }

    public void setSellout(String sellout) {
        this.sellout = sellout;
    }

    public List<ProjectDetailModel> getList() {
        return list;
    }

    public void setList(List<ProjectDetailModel> list) {
        this.list = list;
    }
}
