package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemPushMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemPushMongoDao implements BaseDao<SystemPushMongo> {

    private final MongoTemplate mongoTemplate;

    public SystemPushMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        //super();
    }

    @Override
    public void update(SystemPushMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SystemPushMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SystemPushMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SystemPushMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<SystemPushMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(SystemPushMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SystemPushMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
