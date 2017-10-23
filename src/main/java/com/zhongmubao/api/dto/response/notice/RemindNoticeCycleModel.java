package com.zhongmubao.api.dto.response.notice;

import com.zhongmubao.api.mongo.entity.NotifyCycleMongo;

import java.util.List;

/**
 * 提醒通知类型
 * @author 米立林 2017-10-18
 */
public class RemindNoticeCycleModel {

    private List<NotifyCycleMongo> list;

    public RemindNoticeCycleModel(List<NotifyCycleMongo> list) {
        this.list = list;
    }

    public RemindNoticeCycleModel() {
    }

    public List<NotifyCycleMongo> getList() {
        return list;
    }

    public void setList(List<NotifyCycleMongo> list) {
        this.list = list;
    }
}
