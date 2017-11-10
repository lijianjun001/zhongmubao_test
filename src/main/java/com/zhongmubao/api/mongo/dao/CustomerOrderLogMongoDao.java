package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerOrderLogMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户购羊记录
 *
 * @author 米立林
 */
@Repository
public class CustomerOrderLogMongoDao implements BaseDao<CustomerOrderLogMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerOrderLogMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void update(CustomerOrderLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerOrderLogMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerOrderLogMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerOrderLogMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, CustomerOrderLogMongo.class);
    }

    @Override
    public List<CustomerOrderLogMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerOrderLogMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerOrderLogMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 批量删除
     *
     * @param entitys
     * @throws Exception
     */
    public void delete(List<CustomerOrderLogMongo> entitys) throws Exception {
        mongoTemplate.remove(entitys);
    }

    /**
     * 获取用户购羊记录
     *
     * @param customerId 用户ID
     * @return
     * @throws Exception
     * @author 米立林
     */
    public List<CustomerOrderLogMongo> getList(int customerId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return mongoTemplate.find(query, CustomerOrderLogMongo.class);
    }
}
