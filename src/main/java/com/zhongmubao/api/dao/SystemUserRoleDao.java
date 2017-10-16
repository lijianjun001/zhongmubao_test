package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemUserRoleDao {

    SystemUserRole getSystemUserRolerById(@Param("id") int id);

    List<SystemUserRole> pagerSystemUserRoleList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemUserRole(SystemUserRole systemUserRole);

}
