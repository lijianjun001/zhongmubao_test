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
     * 注册客户
     *
     * @param account     账号
     * @param password    密码
     * @param sign        sign签名
     * @param referenceId 推荐人ID
     */
    void insert(@Param("account") String account, @Param("password") String password, @Param("sing") String sign, @Param("referenceId") int referenceId);
}
