package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.NotifyTypeMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotifyTypeMongoDao implements BaseDao<NotifyTypeMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NotifyTypeMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(NotifyTypeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(NotifyTypeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(NotifyTypeMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public NotifyTypeMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, NotifyTypeMongo.class);
    }

    @Override
    public List<NotifyTypeMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, NotifyTypeMongo.class);
    }

    @Override
    public void delete(NotifyTypeMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<NotifyTypeMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
