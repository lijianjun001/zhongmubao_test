package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.StoreQRCode;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreQRCodeDao {
   
    StoreQRCode getStoreQRCoderById(@Param("id") int id);
    List<StoreQRCode> pagerStoreQRCodeList(@Param("offset") int offset, @Param("limit") int limit);
	int insertStoreQRCode(StoreQRCode storeQRCode);

}
