package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerNoticMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CustomerNoticMongoDao
 *
 * @author xy
 */
@Repository
public class CustomerNoticMongoDao implements BaseDao<CustomerNoticMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerNoticMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerNoticMongo entity) throws Exception {

    }

    @Override
    public void save(CustomerNoticMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerNoticMongo entity) throws Exception {

    }

    @Override
    public CustomerNoticMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<CustomerNoticMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerNoticMongo entity) throws Exception {

    }

    @Override
    public List<CustomerNoticMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
