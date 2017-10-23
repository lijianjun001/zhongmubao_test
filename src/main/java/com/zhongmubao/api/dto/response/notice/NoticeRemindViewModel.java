package com.zhongmubao.api.dto.response.notice;

/**
 * 购羊提醒列表视图model
 *
 * @author 米立林 2017-10-18
 */
public class NoticeRemindViewModel {
    private String id;
    /**
     * 通知类型 (养羊标/商铺标)
     */
    private String notifyTypeStr;
    /**
     * 通知周期 (只一次/每天/每周五/每月18号)
     */
    private String notifyCycStr;
    /**
     * 是否开启 (00/01)
     */
    private String notifyStatus;
    /**
     * 通知消息 （开标前5分钟 购买养羊标(120天)）
     */
    private String notifyStr;

    public NoticeRemindViewModel(String id, String notifyTypeStr, String notifyCycStr, String notifyStatus, String notifyStr) {
        this.id = id;
        this.notifyTypeStr = notifyTypeStr;
        this.notifyCycStr = notifyCycStr;
        this.notifyStatus = notifyStatus;
        this.notifyStr = notifyStr;
    }

    public NoticeRemindViewModel() {
    }

    public String getNotifyTypeStr() {
        return notifyTypeStr;
    }

    public void setNotifyTypeStr(String notifyTypeStr) {
        this.notifyTypeStr = notifyTypeStr;
    }

    public String getNotifyCycStr() {
        return notifyCycStr;
    }

    public void setNotifyCycStr(String notifyCycStr) {
        this.notifyCycStr = notifyCycStr;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getNotifyStr() {
        return notifyStr;
    }

    public void setNotifyStr(String notifyStr) {
        this.notifyStr = notifyStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
