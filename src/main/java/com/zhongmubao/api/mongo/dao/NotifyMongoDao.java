package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.NotifyMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 米立林
 */
@Repository
public class NotifyMongoDao implements BaseDao<NotifyMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NotifyMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(NotifyMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(NotifyMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(NotifyMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public NotifyMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, NotifyMongo.class);
    }

    @Override
    public List<NotifyMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, NotifyMongo.class);
    }

    @Override
    public void delete(NotifyMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<NotifyMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
