package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.util.datasource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购羊等级
 * @author 米立林
 */
public interface SheepLevelDao {

    /**
     * 等级列表
     * @param offset
     * @param limit
     * @return
     */
    List<SheepLevel> pagerSheepLevelList(@Param("offset") int offset, @Param("limit") int limit);
}
