package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustomerDao {

    Customer getCustomerByAccountAndPassword(@Param("account") String account, @Param("password") String password);
    Customer getCustomerById(@Param("id") int id);
    List<Customer> pagerCustomerList(@Param("offset") int offset, @Param("limit") int limit);
    int insertCustomer(@Param("account") String account, @Param("password") String password , @Param("Sign") String sign , @Param("nickName") String nickName , @Param("name") String name , @Param("phone") String phone , @Param("email") String email , @Param("openId") String openId , @Param("cardType") String cardType , @Param("cardNo") String cardNo , @Param("photo") String photo , @Param("referenceId") int referenceId , @Param("isGrantLibrary") boolean isGrantLibrary , @Param("count") int count , @Param("platform") String platform , @Param("registerIP") String registerIP , @Param("registerAddredss") String registerAddredss , @Param("deleted") boolean deleted , @Param("created") Date created , @Param("modified") Date modified , @Param("redeemPassword") String redeemPassword , @Param("enabledFingerprint") boolean enabledFingerprint , @Param("hadaCount") int hadaCount , @Param("isAutoRedeem") boolean isAutoRedeem , @Param("isSetPassword") boolean isSetPassword );

    int addCustomer(Customer customer);
    int UpdatePassword(@Param("id") int id,@Param("password") String password);
    int UpdateRedeemPassword(@Param("id") int id,@Param("redeemPassword") String redeemPassword);
    int UpdateIsAutoRedeem(@Param("id") int id,@Param("isAutoRedeem") int isAutoRedeem);

}
