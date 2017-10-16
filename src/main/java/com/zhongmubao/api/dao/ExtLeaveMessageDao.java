package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtLeaveMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtLeaveMessageDao {

    ExtLeaveMessage getExtLeaveMessagerById(@Param("id") int id);

    List<ExtLeaveMessage> pagerExtLeaveMessageList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtLeaveMessage(ExtLeaveMessage extLeaveMessage);

}
