package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.RedEnvelopeInfoMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享红包信息
 *
 * @author Yekai
 */
@Repository
public class RedEnvelopeInfoMongoDao implements BaseDao<RedEnvelopeInfoMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RedEnvelopeInfoMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(RedEnvelopeInfoMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(RedEnvelopeInfoMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(RedEnvelopeInfoMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public RedEnvelopeInfoMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<RedEnvelopeInfoMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(RedEnvelopeInfoMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<RedEnvelopeInfoMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 小程序红包主键
     *
     * @param redId 主表ID
     * @return list集合
     * @throws Exception Exception
     */
    public List<RedEnvelopeInfoMongo> getListByRedEnvelopeId(String redId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("redEnvelopeId").is(redId));
        //排序
        query.with(new Sort(Sort.Direction.ASC, "joinNo"));

        return mongoTemplate.find(query, RedEnvelopeInfoMongo.class);
    }

    /**
     * 获取单个用户
     *
     * @param customerId 客户ID
     * @param redId      主表ID
     * @return RedEnvelopeInfoMongo
     * @throws Exception Exception
     */
    public RedEnvelopeInfoMongo getRedEnvelopeInfoBy(int customerId, String redId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("JoinCustomerId").is(customerId));
        query.addCriteria(Criteria.where("redEnvelopeId").is(redId));

        return mongoTemplate.findOne(query, RedEnvelopeInfoMongo.class);
    }
}
