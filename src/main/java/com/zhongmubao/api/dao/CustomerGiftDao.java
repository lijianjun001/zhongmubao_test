package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerGift;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerGiftDao {
   
    CustomerGift getCustomerGiftrById(@Param("id") int id);
    List<CustomerGift> pagerCustomerGiftList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerGift(CustomerGift customerGift);

}
