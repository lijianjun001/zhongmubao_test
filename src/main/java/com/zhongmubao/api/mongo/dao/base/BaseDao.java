package com.zhongmubao.api.mongo.dao.base;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 基础操作mongod数据层
 * @author 孙阿龙
 */
public interface BaseDao<T extends BaseModel> {


    /**
     * 修改操作
     * @param entity
     * @throws Exception
     */
    void update(T entity) throws Exception;

    /**
     * save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
     * @param entity
     * @throws Exception
     */
    void save(T entity) throws Exception;

    /**
     * insert的对象如果存在则不会修改之前的值，也不会重新增加
     * @param entity
     * @throws Exception
     */
    void add(T entity) throws Exception;

    /**
     * 获取单个
     * @param query
     * @return
     * @throws Exception
     */
    T get(Query query) throws Exception;

    /**
     * 获取单个
     * @param query
     * @return
     * @throws Exception
     */
    List<T> getList(Query query) throws Exception;

    /**
     * 删除
     * @param entity
     * @throws Exception
     */
    void delete(T entity) throws Exception;

    /**
     * 分页
     * @param query
     * @throws Exception
     */
    List<T> getPage(Query query, int pageIndex, int size) throws Exception;
}