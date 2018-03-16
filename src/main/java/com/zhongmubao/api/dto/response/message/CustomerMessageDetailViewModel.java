package com.zhongmubao.api.dto.response.message;

/**
 * 消息详情
 *
 * @author 米立林
 */
public class CustomerMessageDetailViewModel {
    public CustomerMessageDetailViewModel() {
    }

    public CustomerMessageDetailViewModel(String title, String text, String content) {
        this.title = title;
        this.text = text;
        this.content = content;
    }

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String text;

    /**
     * 内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
