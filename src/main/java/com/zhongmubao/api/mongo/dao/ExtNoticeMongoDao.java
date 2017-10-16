package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.entity.ExtNotice;
import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.zhongmubao.api.mongo.entity.ExtNoticeMongo;

import java.util.List;

public class ExtNoticeMongoDao implements BaseDao<ExtNoticeMongo> {

    private final MongoTemplate mongoTemplate;

    public ExtNoticeMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        //super();
    }

    @Override
    public void update(ExtNoticeMongo entity) throws Exception {

    }

    @Override
    public void save(ExtNoticeMongo entity) throws Exception {

    }

    @Override
    public void add(ExtNoticeMongo entity) throws Exception {

    }

    @Override
    public ExtNoticeMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<ExtNoticeMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(ExtNoticeMongo entity) throws Exception {

    }

    @Override
    public List<ExtNoticeMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }


    public PageModel<ExtNoticeMongo> pager(PageModel<ExtNoticeMongo> page) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Delete").is(0));

        //查询总数
        int count = (int) mongoTemplate.count(query, ExtNoticeMongo.class);
        page.setRowCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "Created"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<ExtNoticeMongo> list = mongoTemplate.find(query, ExtNoticeMongo.class);
        page.setDatas(list);
        return page;
    }

    public int countByCustomerIdAndType() {
        Query query = new Query();
        return (int) mongoTemplate.count(query, ExtNoticeMongo.class);
    }

}
