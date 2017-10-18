package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 通知周期
 * @author 米立林
 * @date 2017-10-18
 */
@Document(collection = "NotifyCycleMongo")
public class NotifyCycleMongo  extends BaseModel {

    @Field("Cycle")
    private String cycle;

    @Field("CycleStr")
    private String cycleStr;

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleStr() {
        return cycleStr;
    }

    public void setCycleStr(String cycleStr) {
        this.cycleStr = cycleStr;
    }
}
