package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtBanner;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtBannerDao {

    ExtBanner getExtBannerrById(@Param("id") int id);

    List<ExtBanner> pagerExtBannerList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtBanner(ExtBanner extBanner);

}
