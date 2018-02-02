package com.zhongmubao.api.dto.response.system;

/**
 * SystemServerActionModel 实体
 *
 * @author 孙阿龙
 */
public class SystemServerActionModel {

    private String objectId;
    private String server;
    private String action;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
