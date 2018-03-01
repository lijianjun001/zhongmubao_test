package com.zhongmubao.api.dao;


import com.github.pagehelper.Page;
import com.zhongmubao.api.entity.SheepOrder;
import com.zhongmubao.api.entity.ext.MySheepRoomSheepOrderAndProject;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.util.datasource.DataSource;
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
     *
     * @param customerId 客户id
     * @param beginTime  开始时间
     * @param endTime    解释鼠键
     * @param states     状态
     * @return 订单数
     */
    int countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(@Param("customerId") int customerId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("states") List<String> states);

    /**
     * 统计用户订单数
     *
     * @param customerId 客户id
     * @param states     状态
     * @return 订单数
     */
    int countSheepOrderByCustomerIdAndState(@Param("customerId") int customerId, @Param("states") List<String> states);


    /**
     * 获取 标 和 订单 数据 （特殊字段 我的羊圈 使用）
     *
     * @param customerId 客户id
     * @param states     状态（List）
     * @param type       类型 00 or 03  默认00
     * @return List<MySheepRoomSheepOrderAndProject>
     */
    List<MySheepRoomSheepOrderAndProject> getSheepOrderAndProjectByCustomeridAndStatesAndType(@Param("customerId") int customerId, @Param("states") List<String> states, @Param("type") int type);

    /**
     * 获取 订单表
     *
     * @param customerId 客户Id
     * @param projectId  项目Id
     * @return List<SheepOrder>
     */
    List<SheepOrder> getSheepOrderByProjectIdAndCustomerId(@Param("customerId") int customerId, @Param("projectId") int projectId);

    /**
     * 根据ProjectId获取购羊订单
     *
     * @param customerId 用户ID
     * @param states     订单状态
     * @param type       "00" 羊只 "03"店铺
     * @return
     */
    Page<SheepOrderInfo> pageSheepOrderByCustomerIdGroupByProjectId(@Param("customerId") int customerId, @Param("states") List<String> states, @Param("type") String type);

    /**
     * 获取所有购羊数（包括羊只和店铺）
     *
     * @param customerId 用户ID
     * @param states     订单状态
     * @return
     */
    int sumSheepOrderCountByCustomerIdAndState(@Param("customerId") int customerId, @Param("states") List<String> states);

    /**
     * 羊标订单列表
     *
     * @param customerId 用户ID
     * @param projectId  羊标ID
     * @param states     订单状态
     * @return List<SheepOrderInfo>
     */
    List<SheepOrderInfo> getOrderByCustomerIdAndProjectIdAndState(@Param("customerId") int customerId, @Param("projectId") int projectId, @Param("states") List<String> states);

    /**
     * 统计购羊收益，仅统计已赎回羊只
     *
     * @param customerId 用户ID
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @return List<SheepOrderInfo>
     */
    List<SheepOrderInfo> statisticsBuySheepIncome(@Param("customerId") int customerId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
