package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerQRCodePackageLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerQRCodePackageLogDao {

    CustomerQRCodePackageLog getCustomerQRCodePackageLogrById(@Param("id") int id);

    List<CustomerQRCodePackageLog> pagerCustomerQRCodePackageLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerQRCodePackageLog(CustomerQRCodePackageLog customerQRCodePackageLog);

}
