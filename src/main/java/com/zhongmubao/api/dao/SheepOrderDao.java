package com.zhongmubao.api.dao;


import com.zhongmubao.api.entity.ext.MySheepRoomSheepOrderAndProject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 顶到数据层
 *
 * @author 孙阿龙
 */
public interface SheepOrderDao {
    /**
     * 统计用户订单数
     * @param customerId 客户id
     * @param beginTime 开始时间
     * @param endTime 解释鼠键
     * @param states 状态
     * @return 订单数
     */
    int countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(@Param("customerId") int customerId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("states") List<String> states);

    /**
     *
     * @param customerId 客户id
     * @param states 状态（List）
     * @param Type 类型 00 or 03  默认00
     * @return List<MySheepRoomSheepOrderAndProject>
     */
    List<MySheepRoomSheepOrderAndProject> getSheepOrderAndProjectByCustomeridAndStatesAndType(@Param("customerId") int customerId, @Param("states") List<String> states, @Param("customerId") int Type);
}
