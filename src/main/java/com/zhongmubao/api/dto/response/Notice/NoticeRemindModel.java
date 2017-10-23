package com.zhongmubao.api.dto.response.Notice;

import java.util.List;

/**
 * 购羊提醒
 * @author 米立林
 */
public class NoticeRemindModel {

    private List<NoticeRemindViewModel> list;

    public NoticeRemindModel(List<NoticeRemindViewModel> list) {
        this.list = list;
    }

    public NoticeRemindModel() {
    }

    public List<NoticeRemindViewModel> getList() {
        return list;
    }

    public void setList(List<NoticeRemindViewModel> list) {
        this.list = list;
    }
}
