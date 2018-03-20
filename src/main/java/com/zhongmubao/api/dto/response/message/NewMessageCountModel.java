package com.zhongmubao.api.dto.response.message;

/**
 * 新消息数量
 *
 * @author 米立林
 */
public class NewMessageCountModel {
    public NewMessageCountModel() {
    }

    public NewMessageCountModel(int count) {
        this.count = count;
    }

    /**
     * 数量
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
