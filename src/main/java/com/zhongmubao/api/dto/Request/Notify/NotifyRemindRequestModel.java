package com.zhongmubao.api.dto.Request.Notify;

import com.zhongmubao.api.config.enmu.NotifyRemindState;

public class NotifyRemindRequestModel {

    private String id;
    private NotifyRemindState status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotifyRemindState getStatus() {
        return status;
    }

    public void setStatus(NotifyRemindState status) {
        this.status = status;
    }
}
