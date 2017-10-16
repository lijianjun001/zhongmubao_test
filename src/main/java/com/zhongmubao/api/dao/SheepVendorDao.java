package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepVendor;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepVendorDao {
   
    SheepVendor getSheepVendorrById(@Param("id") int id);
    List<SheepVendor> pagerSheepVendorList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepVendor(SheepVendor sheepVendor);
    List<SheepVendor> getSheepVendorList();

}
