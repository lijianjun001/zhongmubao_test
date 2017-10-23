package com.zhongmubao.api.dto.request.notify;

/**
 * 添加购羊提醒参数提示
 * @author 米立林 2017-10-18
 */
public class NotifyRemindSaveRequestModel {
    private int time;
    private String cycle;
    private String type;
    private String selectDate;

    public NotifyRemindSaveRequestModel(int time, String cycle, String type, String selectDate) {
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

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }
}
