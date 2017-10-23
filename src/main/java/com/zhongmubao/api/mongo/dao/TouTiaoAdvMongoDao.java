package com.zhongmubao.api.mongo.dao;

import com.zhongmubao.api.mongo.dao.base.BaseDao;
import com.zhongmubao.api.mongo.entity.TouTiaoAdvMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouTiaoAdvMongoDao implements BaseDao<TouTiaoAdvMongo> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TouTiaoAdvMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public void update(TouTiaoAdvMongo entity) throws Exception {

    }
    public void updateFirst(Query query, Update update) throws Exception {
        this.mongoTemplate.updateFirst(query,update,"TouTiaoAdvMongo");
    }
    @Override
    public void save(TouTiaoAdvMongo entity) throws Exception {

    }

    @Override
    public void add(TouTiaoAdvMongo entity) throws Exception {

    }

    public TouTiaoAdvMongo getOrderBy(Criteria criteria) throws Exception {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.sort(new Sort(Sort.Direction.DESC,"_id")),
                Aggregation.limit(1)
        );

        return mongoTemplate.aggregate(aggregation,"TouTiaoAdvMongo",TouTiaoAdvMongo.class).getUniqueMappedResult();
    }
    @Override
    public TouTiaoAdvMongo get(Query query) throws Exception {
        return null;
    }

    @Override
    public List<TouTiaoAdvMongo> getList(Query query) throws Exception {
        return null;
    }

    @Override
    public void delete(TouTiaoAdvMongo entity) throws Exception {

    }

    @Override
    public List<TouTiaoAdvMongo> getPage(Query query, int pageIndex, int size) throws Exception {
        return null;
    }
}
