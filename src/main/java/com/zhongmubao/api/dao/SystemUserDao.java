package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemUserDao {

    SystemUser getSystemUserrById(@Param("id") int id);

    List<SystemUser> pagerSystemUserList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemUser(SystemUser systemUser);

}
