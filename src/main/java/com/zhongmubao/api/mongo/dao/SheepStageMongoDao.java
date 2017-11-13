package com.zhongmubao.api.mongo.dao;


import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SheepStageMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 养殖流程
 *
 * @author 米立林
 */
@Repository
public class SheepStageMongoDao implements BaseDao<SheepStageMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SheepStageMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(SheepStageMongo entity) throws Exception {

    }

    @Override
    public void save(SheepStageMongo entity) throws Exception {

    }

    @Override
    public void add(SheepStageMongo entity) throws Exception {

    }

    @Override
    public SheepStageMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<SheepStageMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(SheepStageMongo entity) throws Exception {

    }

    @Override
    public List<SheepStageMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }


    /**
     * 获取养殖流程
     *
     * @param period 周期
     * @param type   类型
     * @return
     * @throws Exception
     */
    public List<SheepStageMongo> getListByPeriodAndType(int period, String type) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Period").is(period));
        query.addCriteria(Criteria.where("Type").is(type));
        return mongoTemplate.find(query, SheepStageMongo.class);
    }
}
