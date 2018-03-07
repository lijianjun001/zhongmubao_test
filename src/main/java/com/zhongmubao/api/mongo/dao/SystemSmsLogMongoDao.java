package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemSmsLogMongo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统短信日志
 *
 * @author 米立林
 */
@Repository
public class SystemSmsLogMongoDao implements BaseDao<SystemSmsLogMongo> {
    private final MongoTemplate mongoTemplate;

    public SystemSmsLogMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
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
        return mongoTemplate.findOne(query, SystemSmsLogMongo.class);
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
     * 获取最新验证码
     *
     * @param phone 手机号
     * @param type  类型
     * @return SystemSmsLogMongo
     * @throws Exception
     */
    public SystemSmsLogMongo getFirstOrderByCreatedDesc(String phone, String type) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Phone").is(phone));
        query.addCriteria(Criteria.where("Type").is(type));
        return mongoTemplate.findOne(query, SystemSmsLogMongo.class);
    }
}
