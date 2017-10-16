package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepPhotoDao {
   
    SheepPhoto getSheepPhotorById(@Param("id") int id);
    List<SheepPhoto> pagerSheepPhotoList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepPhoto(SheepPhoto sheepPhoto);

}
