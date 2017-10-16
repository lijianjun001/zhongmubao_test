package com.zhongmubao.api.mongo.entity.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Foghost
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1034671331121855153L;

    /**
     * 主键
     */
    public String id;

}
