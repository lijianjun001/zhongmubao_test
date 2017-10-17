package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SheepStageMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SheepStageMongoDao implements BaseDao<SheepStageMongo> {

    private final MongoTemplate mongoTemplate;

    public SheepStageMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        //super();
    }

    @Override
    public void update(SheepStageMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SheepStageMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SheepStageMongo entity) throws Exception {
        mongoTemplate.insert(entity);
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
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SheepStageMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * @return 通过周期获取养殖流程
     * @throws Exception
     */
    public List<SheepStageMongo> getListByPeriod(int period,int type) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Period").is(period));
        query.addCriteria(Criteria.where("Type").is(type));
        return mongoTemplate.find(query, SheepStageMongo.class);
    }
}
