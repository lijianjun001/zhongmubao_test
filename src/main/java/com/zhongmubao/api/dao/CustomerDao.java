package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.util.datasource.DataSource;
import org.apache.ibatis.annotations.Param;

/**
 * 客户数据层
 *
 * @author 孙阿龙
 */
public interface CustomerDao {

    /**
     * 根据id获取用户
     *
     * @param id 客户id
     * @return 客户
     */
    Customer getCustomerById(@Param("id") int id);

    /**
     * 根据id获取用户
     *
     * @param account 注册手机号
     * @return 客户
     */
    Customer getCustomerByAccount(@Param("account") String account);

    /**
     * 注册客户
     *
     * @param customer 推荐人ID
     */
    void insert(Customer customer);

    /**
     * 更新微信OpenId
     *
     * @param id       客户主键
     * @param openId   微信Id
     * @param modified 修改时间
     */
    void updateOpenId(@Param("int") int id, @Param("openId") String openId, @Param("modified") String modified);
}
