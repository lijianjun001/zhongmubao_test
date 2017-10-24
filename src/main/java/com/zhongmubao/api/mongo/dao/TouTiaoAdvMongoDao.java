package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemTokenMongo;
import com.zhongmubao.api.mongo.entity.TouTiaoAdvMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouTiaoAdvMongoDao implements BaseDao<TouTiaoAdvMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TouTiaoAdvMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 更新
     * @param entity
     * @throws Exception 异常
     */
    @Override
    public void update(TouTiaoAdvMongo entity) throws Exception {
        this.mongoTemplate.save(entity);
    }

    @Override
    public void save(TouTiaoAdvMongo entity) throws Exception {

    }

    @Override
    public void add(TouTiaoAdvMongo entity) throws Exception {

    }

    @Override
    public TouTiaoAdvMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<TouTiaoAdvMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(TouTiaoAdvMongo entity) throws Exception {

    }

    @Override
    public List<TouTiaoAdvMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取头条广告
     *
     * @param imei   imei
     * @param os     os
     * @param status 状态
     * @return TouTiaoAdvMongo
     * @throws Exception 异常
     */
    public TouTiaoAdvMongo getByImeiAndOsAndStatus(String imei, String os, String status) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("imei").is(imei));
        query.addCriteria(Criteria.where("os").is(os));
        query.addCriteria(Criteria.where("status").is(status));
        return this.get(query);
    }
}
