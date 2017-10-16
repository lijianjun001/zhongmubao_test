package com.zhongmubao.api.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhongmubao.api.entity.ExtRedPackage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtRedPackageDao {

    ExtRedPackage getEffectiveExtRedPackageByCustomerIdAndId(@Param("customerId") int customerId, @Param("id") int id);
    List<ExtRedPackage> pagerExtRedPackageList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtRedPackage(ExtRedPackage extRedPackage);
    int countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(@Param("customerId") int customerId,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,@Param("type") String type);
    int countExtRedPackageByBeginTimeAndEndTimeAndType(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,@Param("type") String type);
    int countLtPriceExtRedPackageByBeginTimeAndEndTimeAndType(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,@Param("type") String type,@Param("price") double price);
    int updateExtRedPackageExpTimeByCustomerIdAndId(@Param("customerId") int customerId, @Param("id") int id,@Param("expTime") Date beginTime);
    List<ExtRedPackage> getEffectiveExtRedPackageByCustomerIdAndIds(@Param("customerId") int customerId, @Param("ids") List<Integer> ids);
    List<ExtRedPackage> pageEffectiveExtRedPackageByCustomer(@Param("customerId") int customerId, @Param("ids") List<Integer> ids);
    void updateExtRedPackageIsUsedByCustomerIdAndIds(@Param("customerId") int customerId, @Param("ids") List<Integer> ids);
    Page<ExtRedPackage> pageEffectiveExtRedPackageByCustomerIdOrderByType(@Param("customerId") int customerId,@Param("type")String type);

    /**
     * 获取有效红包列表
     * @param customerId 用户主键ID
     * @param sortType   排序方式，Price or ExpTime
     * @return  红包列表
     */
    Page<ExtRedPackage> pageEffectiveExtRedPackageByCustomerId(@Param("customerId") int customerId,@Param("sortType")String sortType);

}
