package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtFailedLogin;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtFailedLoginDao {

    ExtFailedLogin getExtFailedLoginrById(@Param("id") int id);

    List<ExtFailedLogin> pagerExtFailedLoginList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtFailedLogin(ExtFailedLogin extFailedLogin);

}
