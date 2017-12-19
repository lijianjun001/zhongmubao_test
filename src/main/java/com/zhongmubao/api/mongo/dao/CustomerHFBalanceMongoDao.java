package com.zhongmubao.api.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.CustomerHFBalanceMongo;
import com.zhongmubao.api.mongo.entity.ShareCardMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 汇付交易余额变动记录
 *
 * @author 米立林
 */
@Repository
public class CustomerHFBalanceMongoDao implements BaseDao<CustomerHFBalanceMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerHFBalanceMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(CustomerHFBalanceMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(CustomerHFBalanceMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(CustomerHFBalanceMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public CustomerHFBalanceMongo get(org.springframework.data.mongodb.core.query.Query query) throws Exception {
        return null;
    }

    @Override
    public List<CustomerHFBalanceMongo> getList(org.springframework.data.mongodb.core.query.Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(CustomerHFBalanceMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<CustomerHFBalanceMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取交易明细
     *
     * @param customerId 用户
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param type       交易类型
     * @param page       分页
     * @return PageModel<CustomerHFBalanceMongo>
     * @author 米立林 2017-12-11
     */
    public PageModel<CustomerHFBalanceMongo> pager(int customerId, Date startDate, Date endDate, String type, PageModel<CustomerHFBalanceMongo> page) {

        DBObject obj = new BasicDBObject();
        DBObject objl = new BasicDBObject();
        //开始时间查询
        objl.put("$gte", startDate);
        obj.put("CreateTime", objl);
        //结束时间查询
        objl.put("$lte", endDate);
        obj.put("CreateTime", objl);
        Query query = new BasicQuery(obj);

        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        if (!StringUtil.isNullOrEmpty(type)) {
            query.addCriteria(Criteria.where("Type").is(type));
        }

        //查询总数
        int count = (int) mongoTemplate.count(query, CustomerHFBalanceMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "CreateTime"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<CustomerHFBalanceMongo> list = mongoTemplate.find(query, CustomerHFBalanceMongo.class);
        page.setDatas(list);
        return page;
    }

    /**
     * 获取单个交易明细
     *
     * @param customerId 用户
     * @param id         交易主键
     * @return CustomerHFBalanceMongo
     * @author 米立林 2017-12-13
     */
    public CustomerHFBalanceMongo getSingle(int customerId, String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return mongoTemplate.findOne(query, CustomerHFBalanceMongo.class);
    }

    /**
     * 计算交易总额
     *
     * @param customerId 用户
     * @param type       交易类型
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @return double
     * @author 米立林 2017-12-15
     */
    public List<CustomerHFBalanceMongo> calcTransactionTotalAmount(int customerId, String type, Date startDate, Date endDate) {
        DBObject obj = new BasicDBObject();
        DBObject objl = new BasicDBObject();
        //开始时间查询
        objl.put("$gte", startDate);
        obj.put("CreateTime", objl);
        //结束时间查询
        objl.put("$lte", endDate);
        obj.put("CreateTime", objl);
        Query query = new BasicQuery(obj);

        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        if (!StringUtil.isNullOrEmpty(type)) {
            query.addCriteria(Criteria.where("Type").is(type));
        }

        return mongoTemplate.find(query, CustomerHFBalanceMongo.class);
    }
}
