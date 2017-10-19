package com.zhongmubao.api.mongo.dao;


import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerOrderLogMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerOrderLogMongoDao implements BaseDao<CustomerOrderLogMongo> {
    private final MongoTemplate mongoTemplate;
    @Autowired
    public CustomerOrderLogMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long count(Query query)throws Exception {
        return  mongoTemplate.count(query,CustomerOrderLogMongo.class);
    }

    @Override
    public void update(CustomerOrderLogMongo entity) throws Exception {

    }

    @Override
    public void save(CustomerOrderLogMongo entity) throws Exception {

    }

    @Override
    public void add(CustomerOrderLogMongo entity) throws Exception {

    }

    @Override
    public CustomerOrderLogMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query,CustomerOrderLogMongo.class);
    }

    @Override
    public List<CustomerOrderLogMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerOrderLogMongo entity) throws Exception {
        mongoTemplate.remove(entity,"CustomerOrderLogMongo");
    }

    @Override
    public List<CustomerOrderLogMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
