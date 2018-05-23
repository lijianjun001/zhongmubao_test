package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.LoginIpBlackListMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录ip黑名单数据访问层
 *
 * @author 米立林
 */
@Repository
public class LoginIpBlackListMongoDao implements BaseDao<LoginIpBlackListMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LoginIpBlackListMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(LoginIpBlackListMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(LoginIpBlackListMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(LoginIpBlackListMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public LoginIpBlackListMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<LoginIpBlackListMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(LoginIpBlackListMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<LoginIpBlackListMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 通过IP获取实体
     * @param ip ip地址

     * @return LoginIpBlackListMongo
     */
    public LoginIpBlackListMongo getByIp(String ip) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Ip").is(ip));
        return mongoTemplate.findOne(query, LoginIpBlackListMongo.class);
    }
}
