package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustomerDao {

    Customer getCustomerByAccountAndPassword(@Param("account") String account, @Param("password") String password);
    Customer getCustomerById(@Param("id") int id);
    List<Customer> pagerCustomerList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomer(@Param("account") String account, @Param("password") String password, @Param("sign") String sign, @Param("nickName") String nickName, @Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("openId") String openId, @Param("cardType") String cardType, @Param("cardNo") String cardNo, @Param("photo") String photo, @Param("referenceId") int referenceId, @Param("isGrantLibrary") boolean isGrantLibrary, @Param("count") int count, @Param("platform") String platform, @Param("registerIP") String registerIP, @Param("registerAddredss") String registerAddredss, @Param("deleted") boolean deleted, @Param("created") Date created, @Param("modified") Date modified, @Param("redeemPassword") String redeemPassword, @Param("enabledFingerprint") boolean enabledFingerprint, @Param("hadaCount") int hadaCount, @Param("isAutoRedeem") boolean isAutoRedeem, @Param("isSetPassword") boolean isSetPassword);

    /**
     * 添加用户
     * @param customer
     * @return 受影响的行数
     */
    int addCustomer(Customer customer);

    /**
     * 修改登录密码
     * @param id 用户id
     * @param password 密码
     * @return 受影响的行数
     * @author 米立林
     */
    int updatePassword(@Param("id") int id, @Param("password") String password);

    /**
     * 修改赎回密码
     * @param id 用户id
     * @param redeemPassword 赎回密码
     * @return 受影响的行数
     * @author 米立林
     */
    int updateRedeemPassword(@Param("id") int id, @Param("redeemPassword") String redeemPassword);

    /**
     * 是否开启自动赎回
     * @param id 用户id
     * @param isAutoRedeem 是否自动赎回
     * @return 1成功 0失败
     * @author 米立林
     */
    int updateIsAutoRedeem(@Param("id") int id, @Param("isAutoRedeem") int isAutoRedeem);

}
