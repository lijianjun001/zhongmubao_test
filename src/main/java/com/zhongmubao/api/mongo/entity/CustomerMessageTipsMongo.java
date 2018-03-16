package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 客户消息标记
 *
 * @author 米立林
 */
@Document(collection = "CustomerMessageTipsMongo")
public class CustomerMessageTipsMongo extends BaseModel {
    /**
     * 唯一标识
     */
    @Field("Identification")
    private int identification;

    /**
     * 标记名（有惊喜、有更新、巨有料、值得看等）
     */
    @Field("Name")
    private String name;

    /**
     * 标记背景颜色
     */
    @Field("BackColor")
    private String backColor;

    /**
     * 添加时间
     */
    @Field("Created")
    private Date created;

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
