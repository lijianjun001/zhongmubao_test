package com.zhongmubao.api.dto.response.message;

/**
 * 新消息数量
 *
 * @author 米立林
 */
public class NewMessageCountViewModel {
    public NewMessageCountViewModel() {
    }

    public NewMessageCountViewModel(int count) {
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
