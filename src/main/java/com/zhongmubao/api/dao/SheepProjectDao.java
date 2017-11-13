package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepProject;
import org.apache.ibatis.annotations.Param;

/**
 * @author 米立林
 */
public interface SheepProjectDao {

    /**
     * 通过主键ID获取羊标
     * @param id 主键
     * @return
     */
    SheepProject getSheepProjectById(@Param("id") int id);
}
