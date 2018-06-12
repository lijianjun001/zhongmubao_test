package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.RedEnvelopeSignMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RedEnvelopeSignMongoDao
 *
 * @author 孙阿龙
 */
@Repository
public class RedEnvelopeSignMongoDao implements BaseDao<RedEnvelopeSignMongo> {

    private final MongoTemplate mongoTemplate;

    public RedEnvelopeSignMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(RedEnvelopeSignMongo entity) throws Exception {

    }

    @Override
    public void save(RedEnvelopeSignMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(RedEnvelopeSignMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public RedEnvelopeSignMongo get(Query query) throws Exception {
        return null;
    }

    public RedEnvelopeSignMongo getBy(int customerId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("customerId").is(customerId));

        return mongoTemplate.findOne(query, RedEnvelopeSignMongo.class);
    }

    @Override
    public List<RedEnvelopeSignMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(RedEnvelopeSignMongo entity) throws Exception {

    }

    @Override
    public List<RedEnvelopeSignMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
