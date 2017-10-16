package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemRoleDao {

    SystemRole getSystemRolerById(@Param("id") int id);

    List<SystemRole> pagerSystemRoleList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemRole(SystemRole systemRole);

}
