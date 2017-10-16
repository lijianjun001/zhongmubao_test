package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerAddressDao {

    CustomerAddress getCustomerAddressrById(@Param("id") int id);

    List<CustomerAddress> pagerCustomerAddressList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerAddress(CustomerAddress customerAddress);

    CustomerAddress getCustomerAddressByCustomerId(@Param("customerId") int customerId);

    int deleteCustomerAddress(@Param("customerId") int customerId, @Param("id") int id);

    int logicDeleteByIdAndCustomerId(@Param("customerId") int customerId, @Param("id") int id);

    int updateInfoByIdAndCustomerId(@Param("customerId") int customerId, @Param("id") int id, @Param("provinceCode") String provinceCode, @Param("provinceName") String provinceName,
                                    @Param("cityCode") String cityCode, @Param("cityName") String cityName, @Param("countyCode") String countyCode,
                                    @Param("countyName") String countyName, @Param("address") String address, @Param("name") String name,
                                    @Param("phone") String phone, @Param("modified") String modified);

    List<CustomerAddress> getCustomerAddressListByCustomerId(@Param("customerId") int customerId);

}
