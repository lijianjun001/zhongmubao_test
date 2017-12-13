package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerHFIndexMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页显示 汇付弹框
 * @author xy
 */
@Repository
public class CustomerHFIndexMongoDao implements BaseDao<CustomerHFIndexMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerHFIndexMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public void update(CustomerHFIndexMongo entity) throws Exception {

    }

    @Override
    public void save(CustomerHFIndexMongo entity) throws Exception {

    }

    @Override
    public void add(CustomerHFIndexMongo entity) throws Exception {

    }

    @Override
    public CustomerHFIndexMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query,CustomerHFIndexMongo.class);
    }

    @Override
    public List<CustomerHFIndexMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerHFIndexMongo entity) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(entity.getCustomerId()));
        mongoTemplate.remove(query,CustomerHFIndexMongo.class);
    }

    @Override
    public List<CustomerHFIndexMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取
     *
     * @param customerId
     * @return TouTiaoAdvMongo
     * @throws Exception 异常
     */
    public CustomerHFIndexMongo getByCustomerId(int customerId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return this.get(query);
    }
}
