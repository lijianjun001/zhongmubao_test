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

    /**
     * 消息中心
     *
     * @param customerId 客户主键
     * @param size       取条数
     * @param type       消息类型
     * @return List<CustomerMessageMongo>
     * @throws Exception Exception
     */
    public List<CustomerMessageMongo> getListBy(int customerId, int size, String type) throws Exception {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Deleted").is(false));
        query.with(new Sort(Sort.Direction.ASC, "TipsIdentification"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        query.limit(size);

        return mongoTemplate.find(query, CustomerMessageMongo.class);
    }

    /**
     * 获取单条记录
     *
     * @param customerId         客户id
     * @param tipsIdentification tipsIdentification
     * @return CustomerMessageMongo
     * @throws Exception Exception
     */
    public CustomerMessageMongo getBy(int customerId, int tipsIdentification) throws Exception {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("Deleted").is(false));
        query.addCriteria(Criteria.where("TipsIdentification").is(tipsIdentification));
        query.with(new Sort(Sort.Direction.ASC, "TipsIdentification"));
        query.with(new Sort(Sort.Direction.DESC, "Created"));

        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    /**
     * 消息中心分页
     *
     * @param customerId 客户主键
     * @param page       分页
     * @return PageModel<CustomerMessageMongo>
     */
    public PageModel<CustomerMessageMongo> pager(int customerId, PageModel<CustomerMessageMongo> page, String type) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Deleted").is(false));

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

    /**
     * 获取单个消息
     *
     * @param id 主键
     * @return List<CustomerMessageMongo>
     * @throws Exception Exception
     */
    public CustomerMessageMongo get(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    /**
     * 获取单个消息
     *
     * @param id         主键
     * @param customerId 客户id
     * @return List<CustomerMessageMongo>
     * @throws Exception Exception
     */
    public CustomerMessageMongo getBy(String id, int customerId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        return mongoTemplate.findOne(query, CustomerMessageMongo.class);
    }

    /**
     * 获取新消息数
     *
     * @param customerId 主键
     * @return int
     * @throws Exception Exception
     */
    public long getNewCount(int customerId) throws Exception {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("CustomerId").is(0), Criteria.where("CustomerId").is(customerId));
        query.addCriteria(criteria);
        query.addCriteria(Criteria.where("IsRead").is(false));

        return mongoTemplate.count(query, CustomerMessageMongo.class);
    }
}
