package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageReadMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统消息已读管理
 *
 * @author 米立林
 */
@Repository
public class CustomerMessageReadMongoDao implements BaseDao<CustomerMessageReadMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerMessageReadMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerMessageReadMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerMessageReadMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerMessageReadMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerMessageReadMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, CustomerMessageReadMongo.class);
    }

    @Override
    public List<CustomerMessageReadMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerMessageReadMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerMessageReadMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取单个消息已读
     *
     * @param id 主键
     * @return CustomerMessageReadMongo
     * @throws Exception Exception
     */
    public CustomerMessageReadMongo getById(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, CustomerMessageReadMongo.class);
    }

    /**
     * 获取单个消息已读
     *
     * @param customerId 客户主键
     * @param messageId  消息主键
     * @return CustomerMessageReadMongo
     * @throws Exception Exception
     */
    public CustomerMessageReadMongo getByCustoemrIdAndMessageId(int customerId, String messageId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("MessageId").is(messageId));

        return mongoTemplate.findOne(query, CustomerMessageReadMongo.class);
    }
}
