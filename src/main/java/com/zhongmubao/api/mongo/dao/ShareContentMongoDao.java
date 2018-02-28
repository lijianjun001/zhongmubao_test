package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.ShareContentMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享内容数据访问DAO
 *
 * @author 米立林
 */
@Repository
public class ShareContentMongoDao implements BaseDao<ShareContentMongo> {

    private final MongoTemplate mongoTemplate;

    public ShareContentMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(ShareContentMongo entity) throws Exception {

    }

    @Override
    public void save(ShareContentMongo entity) throws Exception {

    }

    @Override
    public void add(ShareContentMongo entity) throws Exception {

    }

    @Override
    public ShareContentMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<ShareContentMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(ShareContentMongo entity) throws Exception {

    }

    @Override
    public List<ShareContentMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 通过类型获取分享内容信息
     *
     * @param name 分享类型
     * @return ShareContentMongo
     */
    public ShareContentMongo getByType(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Name").is(name));
        return mongoTemplate.findOne(query, ShareContentMongo.class);
    }
}
