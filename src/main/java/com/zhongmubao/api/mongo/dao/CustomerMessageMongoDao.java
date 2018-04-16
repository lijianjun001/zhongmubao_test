package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户消息数据访问
 *
 * @author 米立林
 */
@Repository
public class CustomerMessageMongoDao implements BaseDao<CustomerMessageMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerMessageMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerMessageMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerMessageMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerMessageMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerMessageMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    @Override
    public List<CustomerMessageMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }

    @Override
    public void delete(CustomerMessageMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerMessageMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public List<CustomerMessageMongo> getListByCustomerIdAndTypeLimitSize(int customerId, int size, String type) throws Exception {
        Query query = baseQuery();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("Type").is(type));
        query.with(new Sort(Sort.Direction.ASC, "TipsId"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        query.limit(size);

        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }

    public List<CustomerMessageMongo> getListByCustomerId(int customerId) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.with(new Sort(Sort.Direction.ASC, "TipsId"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));

        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }

    public List<CustomerMessageMongo> getListByCustomerIdIsRead(int customerId, boolean isRead) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("IsRead").is(isRead));
        query.with(new Sort(Sort.Direction.ASC, "TipsId"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }

    public CustomerMessageMongo getByCustomerIdAndTipsId(int customerId, int tipsId) throws Exception {
        Query query = baseQuery();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("TipsId").is(tipsId));
        query.with(new Sort(Sort.Direction.ASC, "TipsId"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));

        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    public PageModel<CustomerMessageMongo> pager(int customerId, PageModel<CustomerMessageMongo> page, String type) {
        Query query = baseQuery();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("Type").is(type));

        //查询总数
        int count = (int) mongoTemplate.count(query, CustomerMessageMongo.class);
        page.setRowCount(count);

        //排序
        String firstSort = "IsTop";
        String secondSrot = "Created";
        query.with(new Sort(Sort.Direction.DESC, firstSort));
        query.with(new Sort(Sort.Direction.DESC, secondSrot));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<CustomerMessageMongo> list = mongoTemplate.find(query, CustomerMessageMongo.class);
        page.setDatas(list);

        return page;
    }

    public CustomerMessageMongo getById(String id) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    public CustomerMessageMongo getByCustomerIdAndTipsId(String id, int customerId) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("_id").is(id));
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    public CustomerMessageMongo getByCustomerId(String customerId) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    public long countByCustomerIdAndIsRead(int customerId, boolean isRead) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("IsRead").is(isRead));
        return mongoTemplate.count(query, CustomerMessageMongo.class);
    }

    private Query baseQuery() {
        Query query = new Query();
        query.addCriteria(Criteria.where("Deleted").is(false));
        return query;
    }

    /**
     * 获取当前周开标计划
     *
     * @param type       类型
     * @param title      周标区间
     * @return
     * @throws Exception
     */
    public List<CustomerMessageMongo> getCurrentWeekPlan(String type, String title) throws Exception {
        Query query = baseQuery();
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Title").is(title));
        query.with(new Sort(Sort.Direction.DESC, "Created"));

        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }
}
