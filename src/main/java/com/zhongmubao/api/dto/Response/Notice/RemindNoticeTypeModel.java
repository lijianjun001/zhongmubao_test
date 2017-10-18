package com.zhongmubao.api.dto.Response.Notice;

import com.zhongmubao.api.mongo.entity.NotifyTypeMongo;

import java.util.List;

/**
 * 提醒通知类型
 * @author 米立林 2017-10-18
 */
public class RemindNoticeTypeModel {

    private List<NotifyTypeMongo> list;

    public RemindNoticeTypeModel(List<NotifyTypeMongo> list) {
        this.list = list;
    }

    public RemindNoticeTypeModel() {
    }

    public List<NotifyTypeMongo> getList() {
        return list;
    }

    public void setList(List<NotifyTypeMongo> list) {
        this.list = list;
    }
}
