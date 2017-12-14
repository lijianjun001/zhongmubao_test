package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepProject;
import org.apache.ibatis.annotations.Param;

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
}
