package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemSmsLogMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 米立林
 */
@Repository
public class SystemSmsLogMongoDao implements BaseDao<SystemSmsLogMongo> {
    private final MongoTemplate mongoTemplate;

    public SystemSmsLogMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        //super();
    }

    @Override
    public void update(SystemSmsLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SystemSmsLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SystemSmsLogMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SystemSmsLogMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<SystemSmsLogMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(SystemSmsLogMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SystemSmsLogMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * @return 通过周期获取养殖流程
     * @throws Exception
     */
    public SystemSmsLogMongo getListByPeriod(Query query) throws Exception {
        return mongoTemplate.findOne(query, SystemSmsLogMongo.class);
    }
}
