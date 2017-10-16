package com.zhongmubao.api.mongo.dao;

import com.mongodb.DBObject;
import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.SystemTokenMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemTokenMongoDao implements BaseDao<SystemTokenMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SystemTokenMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(SystemTokenMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SystemTokenMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SystemTokenMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SystemTokenMongo get(Query query) throws Exception {

        return mongoTemplate.findOne(query, SystemTokenMongo.class);
    }

    @Override
    public List<SystemTokenMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, SystemTokenMongo.class);
    }

    @Override
    public void delete(SystemTokenMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SystemTokenMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public PageModel<SystemTokenMongo> Pager(PageModel<SystemTokenMongo> page) {
        Query query = new Query();
        //查询总数
        int count = (int) mongoTemplate.count(query, SystemTokenMongo.class);
        page.setRowCount(count);

        //排序
        //query.with(new Sort(Sort.Direction.ASC, "onumber"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<SystemTokenMongo> list = mongoTemplate.find(query, SystemTokenMongo.class);
        page.setDatas(list);
        return page;
    }

    public SystemTokenMongo getByCustomerIdAndPlatform(int customerId, String platform) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("Platform").is(platform));
        return this.get(query);
    }

    public SystemTokenMongo getByTokenAndPlatform(String token, String platform) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        query.addCriteria(Criteria.where("Platform").is(platform));
//        query.addCriteria(Criteria.where("time").gte(condition.getValue())); //gte: 大于等于
        return this.get(query);
    }
}
