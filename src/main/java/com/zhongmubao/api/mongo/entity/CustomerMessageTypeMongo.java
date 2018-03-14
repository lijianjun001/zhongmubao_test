package com.zhongmubao.api.mongo.entity;


import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 客户消息分类
 * @author 米立林
 */
@Document(collection = "CustomerMessageTypeMongo")
public class CustomerMessageTypeMongo extends BaseModel {
    /**
     * 类型名(个人信息、礼物、开标、银行、活动等)
     */
    @Field("Name")
    private String name;
    /**
     * 图标
     */
    @Field("Icon")
    private String icon;
    /**
     * 创建时间
     */
    @Field("Created")
    private Date created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
