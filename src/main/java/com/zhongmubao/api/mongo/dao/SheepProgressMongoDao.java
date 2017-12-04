package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SheepProgressMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 羊标进度
 *
 * @author 米立林
 */
@Repository
public class SheepProgressMongoDao implements BaseDao<SheepProgressMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SheepProgressMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void update(SheepProgressMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SheepProgressMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SheepProgressMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SheepProgressMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, SheepProgressMongo.class);
    }

    @Override
    public List<SheepProgressMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(SheepProgressMongo entity) throws Exception {

    }

    @Override
    public List<SheepProgressMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取所有羊标进度列表
     *
     * @param type 00羊只，03店铺
     * @param period Period(标天数周期)
     * @return
     * @throws Exception
     * @autthor 米立林
     */
    public List<SheepProgressMongo> getList(String type, int period) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Period").is(period));
        return mongoTemplate.find(query, SheepProgressMongo.class);
    }
}
