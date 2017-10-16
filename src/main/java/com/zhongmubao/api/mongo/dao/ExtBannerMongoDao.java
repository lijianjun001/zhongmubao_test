package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.ExtBannerMongo;
import com.zhongmubao.api.mongo.entity.SystemTokenMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtBannerMongoDao implements BaseDao<ExtBannerMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ExtBannerMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(ExtBannerMongo entity) throws Exception {

    }

    @Override
    public void save(ExtBannerMongo entity) throws Exception {

    }

    @Override
    public void add(ExtBannerMongo entity) throws Exception {

    }

    @Override
    public ExtBannerMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<ExtBannerMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, ExtBannerMongo.class);
    }

    @Override
    public void delete(ExtBannerMongo entity) throws Exception {

    }

    @Override
    public List<ExtBannerMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public PageModel<ExtBannerMongo> Pager(PageModel<ExtBannerMongo> page) {
        Query query = new Query();
        //查询总数
        int count = (int) mongoTemplate.count(query, ExtBannerMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "Sort"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<ExtBannerMongo> list = mongoTemplate.find(query, ExtBannerMongo.class);
        page.setDatas(list);
        return page;
    }
}
