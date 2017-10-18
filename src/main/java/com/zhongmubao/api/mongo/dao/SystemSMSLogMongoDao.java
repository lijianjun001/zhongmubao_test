package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemSMSLogMongo;
import com.zhongmubao.api.util.DateUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public class SystemSMSLogMongoDao  implements BaseDao<SystemSMSLogMongo> {
    private final MongoTemplate mongoTemplate;

    public SystemSMSLogMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        //super();
    }

    @Override
    public void update(SystemSMSLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SystemSMSLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SystemSMSLogMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SystemSMSLogMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<SystemSMSLogMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(SystemSMSLogMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SystemSMSLogMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * @return 通过周期获取养殖流程
     * @throws Exception
     */
    public SystemSMSLogMongo getListByPeriod(Query query) throws Exception {
        return mongoTemplate.findOne(query, SystemSMSLogMongo.class);
    }
}
