package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageTypeMongo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户消息类型数据访问层
 *
 * @author 米立林
 */
@Repository
public class CustomerMessageTypeMongoDao implements BaseDao<CustomerMessageTypeMongo> {

    private final MongoTemplate mongoTemplate;

    public CustomerMessageTypeMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerMessageTypeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerMessageTypeMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerMessageTypeMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerMessageTypeMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, CustomerMessageTypeMongo.class);
    }

    @Override
    public List<CustomerMessageTypeMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, CustomerMessageTypeMongo.class);
    }

    @Override
    public void delete(CustomerMessageTypeMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerMessageTypeMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 通过主键获取实体
     *
     * @param id 主键ID
     * @return CustomerMessageTypeMongo
     * @throws Exception
     * @author 米立林
     */
    public CustomerMessageTypeMongo getById(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, CustomerMessageTypeMongo.class);
    }
}
