package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepProject;
import com.zhongmubao.api.entity.ext.SheepBillInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 米立林
 */
public interface SheepProjectDao {

    /**
     * 通过主键ID获取羊标
     * @param id 主键
     * @return
     */
    SheepProject getSheepProjectById(@Param("id") int id);

    /**
     * 通过用户和订单编号获取羊标标题
     *
     * @param customerId 用户
     * @param code       订单编号
     * @return
     */
    String getProjectTitleByBustomerIdAndCode(@Param("customerId") int customerId, @Param("code") String code);

    /**
     * 购羊账单（有效羊只）
     *
     * @param customerId 用户
     * @param startDate  开始日期
     * @param endDate  开始日期
     * @return
     */
    List<SheepBillInfo> getBuySheepBillByCustomerId(@Param("customerId") int customerId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 最早赎回
     *
     * @param customerId 用户ID
     * @param states     订单状态
     * @return List<SheepOrderInfo>
     */
    Date firstRedeem(@Param("customerId") int customerId, @Param("states") List<String> states);

    /**
     * 最后赎回
     *
     * @param customerId 用户ID
     * @param states     订单状态
     * @return List<SheepOrderInfo>
     */
    Date finallyRedeem(@Param("customerId") int customerId, @Param("states") List<String> states);
}
