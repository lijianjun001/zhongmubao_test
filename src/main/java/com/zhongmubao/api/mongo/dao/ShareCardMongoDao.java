package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.ShareCardMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享卡数据层
 *
 * @author 孙阿龙
 */
@Repository
public class ShareCardMongoDao implements BaseDao<ShareCardMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShareCardMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void update(ShareCardMongo entity) throws Exception {

    }

    @Override
    public void save(ShareCardMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(ShareCardMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public ShareCardMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<ShareCardMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(ShareCardMongo entity) throws Exception {

    }

    @Override
    public List<ShareCardMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public PageModel<ShareCardMongo> pager(int customerId, boolean searchHasDeleted, PageModel<ShareCardMongo> page) {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        if(!searchHasDeleted){
            query.addCriteria(Criteria.where("Delete").is(false));
        }

        //查询总数
        int count = (int) mongoTemplate.count(query, ShareCardMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<ShareCardMongo> list = mongoTemplate.find(query, ShareCardMongo.class);
        page.setDatas(list);
        return page;
    }

    public int countByCustomerIdAndType(int customerId, String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Delete").is(false));
        return (int) mongoTemplate.count(query, ShareCardMongo.class);
    }

    public ShareCardMongo getByCustomerIdAndType(int customerId, String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Delete").is(false));
        return mongoTemplate.findOne(query, ShareCardMongo.class);
    }

    public ShareCardMongo getByCustomerIdAndIdAndType(int customerId,String id, String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("CustomerId").is(customerId));
        query.addCriteria(Criteria.where("_id").is(id));
        query.addCriteria(Criteria.where("Type").is(type));
        query.addCriteria(Criteria.where("Delete").is(false));
        return mongoTemplate.findOne(query, ShareCardMongo.class);
    }
}
