package com.zhongmubao.api.dto.Request.Notify;

import java.util.Date;

/**
 * 添加购羊提醒参数提示
 * @author 米立林 2017-10-18
 */
public class NotifyRemindSaveRequestModel {
    private int time;
    private String cycle;
    private String type;
    private Date selectDate;

    public NotifyRemindSaveRequestModel(int time, String cycle, String type, Date selectDate) {
        this.time = time;
        this.cycle = cycle;
        this.type = type;
        this.selectDate = selectDate;
    }

    public NotifyRemindSaveRequestModel() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }
}
