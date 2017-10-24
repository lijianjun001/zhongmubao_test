package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * 客户数据层
 *
 * @author 孙阿龙
 */
public interface CustomerDao {

    /**
     * 根据id获取用户
     * @param id 客户id
     * @return 客户
     */
    Customer getCustomerById(@Param("id") int id);
}
