package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.ArticleMongo;
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
 * 小程序文章Mongo访问
 *
 * @author 米立林
 */
@Repository
public class ArticleMongoDao implements BaseDao<ArticleMongo> {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ArticleMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(ArticleMongo entity) throws Exception {

    }

    @Override
    public void save(ArticleMongo entity) throws Exception {

    }

    @Override
    public void add(ArticleMongo entity) throws Exception {

    }

    @Override
    public ArticleMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<ArticleMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(ArticleMongo entity) throws Exception {

    }

    @Override
    public List<ArticleMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }

    /**
     * 获取文章列表，分页
     *
     * @param hasDeleted 是否删除
     * @param page       分页信息
     * @return PageModel<ArticleMongo>
     */
    public PageModel<ArticleMongo> pager(boolean hasDeleted, PageModel<ArticleMongo> page) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Deleted").is(hasDeleted));

        //查询总数
        int count = (int) mongoTemplate.count(query, ArticleMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<ArticleMongo> list = mongoTemplate.find(query, ArticleMongo.class);
        page.setDatas(list);
        return page;
    }
}
