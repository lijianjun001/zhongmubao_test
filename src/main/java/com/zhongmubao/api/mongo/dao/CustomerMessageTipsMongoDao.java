package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageTipsMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户消息标记数据访问
 *
 * @author 米立林
 */
@Repository
public class CustomerMessageTipsMongoDao implements BaseDao<CustomerMessageTipsMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerMessageTipsMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerMessageTipsMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerMessageTipsMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerMessageTipsMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerMessageTipsMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, CustomerMessageTipsMongo.class);
    }

    @Override
    public List<CustomerMessageTipsMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, CustomerMessageTipsMongo.class);
    }

    @Override
    public void delete(CustomerMessageTipsMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerMessageTipsMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 通过ID获取实体
     *
     * @param id 主键
     * @return
     * @throws Exception
     */
    public CustomerMessageTipsMongo getById(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, CustomerMessageTipsMongo.class);
    }

    /**
     * 通过Identification获取实体
     *
     * @param identification 主键
     * @return
     * @throws Exception
     */
    public CustomerMessageTipsMongo getByIdentification(int identification) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("Identification").is(identification));
        return mongoTemplate.findOne(query, CustomerMessageTipsMongo.class);
    }
}
