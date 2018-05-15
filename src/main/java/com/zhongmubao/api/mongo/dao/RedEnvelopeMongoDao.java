package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.RedEnvelopeMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 发起红包
 *
 * @author Yekai
 */
@Repository
public class RedEnvelopeMongoDao implements BaseDao<RedEnvelopeMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RedEnvelopeMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(RedEnvelopeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(RedEnvelopeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(RedEnvelopeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public RedEnvelopeMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<RedEnvelopeMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(RedEnvelopeMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<RedEnvelopeMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 读取单个
     *
     * @param id 主键
     * @return 实体
     */
    public RedEnvelopeMongo getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, RedEnvelopeMongo.class);
    }
}
