package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.PlatformTrackingMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PlatformTrackingMongoDao
 *
 * @author 孙阿龙
 */
@Repository
public class PlatformTrackingMongoDao implements BaseDao<PlatformTrackingMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PlatformTrackingMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(PlatformTrackingMongo entity) throws Exception {

    }

    @Override
    public void save(PlatformTrackingMongo entity) throws Exception {

    }

    @Override
    public void add(PlatformTrackingMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public PlatformTrackingMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<PlatformTrackingMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(PlatformTrackingMongo entity) throws Exception {

    }

    @Override
    public List<PlatformTrackingMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
