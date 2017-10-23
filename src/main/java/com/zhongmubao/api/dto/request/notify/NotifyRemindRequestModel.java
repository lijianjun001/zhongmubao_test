package com.zhongmubao.api.dto.request.notify;

import com.zhongmubao.api.config.enmu.NotifyRemindState;

/**
 * @author 米立林
 */
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
