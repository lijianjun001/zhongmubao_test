package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.ShareCardMongo;
import com.zhongmubao.api.mongo.entity.SystemServerActionMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.util.StringUtil;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SystemServerActionMongoDao
 *
 * @author 孙阿龙
 */
@Repository
public class SystemServerActionMongoDao implements BaseDao<SystemServerActionMongo> {
    private final MongoTemplate mongoTemplate;

    public SystemServerActionMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(SystemServerActionMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void save(SystemServerActionMongo entity) throws Exception {
        mongoTemplate.save(entity);
    }

    @Override
    public void add(SystemServerActionMongo entity) throws Exception {
        mongoTemplate.insert(entity);
    }

    @Override
    public SystemServerActionMongo get(Query query) throws Exception {
        return mongoTemplate.findOne(query, SystemServerActionMongo.class);
    }

    public SystemServerActionMongo get(String objectId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(objectId)));
        return this.get(query);
    }

    @Override
    public List<SystemServerActionMongo> getList(Query query) throws Exception {
        return mongoTemplate.find(query, SystemServerActionMongo.class);
    }

    @Override
    public void delete(SystemServerActionMongo entity) throws Exception {
        mongoTemplate.remove(entity);
    }

    @Override
    public List<SystemServerActionMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    public PageModel<SystemServerActionMongo> pager(String name, boolean isParent, PageModel<SystemServerActionMongo> page) {
        Query query = new Query();
        if (!StringUtil.isNullOrEmpty(name)) {
            query.addCriteria(Criteria.where("Name").is(name));
        }
        if (!isParent) {
            query.addCriteria(Criteria.where("ParentObjectId").ne("").ne(null));
        }

        //查询总数
        int count = (int) mongoTemplate.count(query, SystemServerActionMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<SystemServerActionMongo> list = mongoTemplate.find(query, SystemServerActionMongo.class);
        page.setDatas(list);
        return page;
    }

    /**
     * 获取所有server
     *
     * @return
     * @throws Exception
     */
    public List<SystemServerActionMongo> getListByParentObjectId(String parentObjectId) throws Exception {
        Query query = new Query();
        if (StringUtil.isNullOrEmpty(parentObjectId)) {
            query.addCriteria(Criteria.where("ParentObjectId").ne("").ne(null));
        } else {
            query.addCriteria(Criteria.where("ParentObjectId").is(parentObjectId));
        }
        return this.getList(query);
    }
}
