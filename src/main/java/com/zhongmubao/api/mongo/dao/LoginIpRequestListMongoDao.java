package com.zhongmubao.api.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.LoginIpRequestListMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 登录IP请求记录列表
 *
 * @author 米立林
 */
@Repository
public class LoginIpRequestListMongoDao  implements BaseDao<LoginIpRequestListMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LoginIpRequestListMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(LoginIpRequestListMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(LoginIpRequestListMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(LoginIpRequestListMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public LoginIpRequestListMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<LoginIpRequestListMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(LoginIpRequestListMongo entity) throws Exception {

    }

    @Override
    public List<LoginIpRequestListMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public long  getCountByIpAndTime(String ip, Date startTime,Date entTime){
        DBObject obj = new BasicDBObject();
        DBObject objl = new BasicDBObject();
        //开始时间查询
        objl.put("$gte", startTime);
        obj.put("Created", objl);
        //结束时间查询
        objl.put("$lte", entTime);
        obj.put("Created", objl);
        Query query = new BasicQuery(obj);
        query.addCriteria(Criteria.where("Ip").is(ip));

        return mongoTemplate.count(query, LoginIpRequestListMongo.class);
    }
}
