package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtFailedLogin;
import org.apache.ibatis.annotations.Param;

/**
 * 登录失败记录数据访问层
 *
 * @author 米立林
 */
public interface ExtFailedLoginDao {

    /**
     * 根据Account获取登录记录
     *
     * @param account 登录手机号
     * @return 登录失败记录
     */
    ExtFailedLogin getExtFailedLogin(@Param("account") String account);
}
