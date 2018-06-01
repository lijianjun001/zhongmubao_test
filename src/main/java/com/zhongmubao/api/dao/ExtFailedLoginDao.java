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

    /**
     * 添加登录记录
     * @param loginRecord 实体
     * @return 0 1
     */
    int insert(ExtFailedLogin loginRecord);

    /**
     * 验证码置空
     *
     * @param account  登录手机号
     * @param code     验证码
     * @param modified 修改时间
     * @return 登录失败记录
     */
    void update(@Param("account") String account, @Param("code") String code, @Param("modified") String modified);

    /**
     * 更新登录错误次数
     *
     * @param account  登录手机号
     * @param failedNum     验证码
     * @param modified 修改时间
     * @return 登录失败记录
     */
    void updateFailedNum(@Param("account") String account, @Param("failedNum") int failedNum, @Param("modified") String modified);
}
