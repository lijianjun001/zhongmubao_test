package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtShareArticle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtShareArticleDao {

    ExtShareArticle getExtShareArticlerById(@Param("id") int id);

    List<ExtShareArticle> pagerExtShareArticleList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtShareArticle(ExtShareArticle extShareArticle);

}
