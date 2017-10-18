package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.NotifyCycleMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotifyCycleMongoDao implements BaseDao<NotifyCycleMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NotifyCycleMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(NotifyCycleMongo entity) throws Exception {

    }

    @Override
    public void save(NotifyCycleMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(NotifyCycleMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public NotifyCycleMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, NotifyCycleMongo.class);
    }

    @Override
    public List<NotifyCycleMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, NotifyCycleMongo.class);
    }

    @Override
    public void delete(NotifyCycleMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<NotifyCycleMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
