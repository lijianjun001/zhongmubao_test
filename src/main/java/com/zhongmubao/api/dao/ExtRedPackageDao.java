package com.zhongmubao.api.dao;

import com.github.pagehelper.Page;
import com.zhongmubao.api.entity.ExtRedPackage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 红包数据层
 *
 * @author 孙阿龙
 */
public interface ExtRedPackageDao {

    /**
     * 单个用户有效红包
     * @param customerId upmgjiod
     * @param id 红包id
     * @return 红包
     */
    ExtRedPackage getEffectiveExtRedPackageByCustomerIdAndId(@Param("customerId") int customerId, @Param("id") int id);

    /**
     * 添加红包
     * @param extRedPackage 红包对象
     * @return 0 1
     */
    int insertExtRedPackage(ExtRedPackage extRedPackage);

    /**
     * 统计用户红包数
     * @param customerId 客户id
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param type 红包类型
     * @return 用户红包数
     */
    int countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(@Param("customerId") int customerId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("type") String type);

    /**
     * 统计用户红包数
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param type 类型
     * @return 红包数
     */
    int countExtRedPackageByBeginTimeAndEndTimeAndType(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("type") String type);

    /**
     * 统计大于某个金额的红包数量
     * @param beginTime  开始时间
     * @param endTime 结束时间
     * @param type 红包类型
     * @param price 金额
     * @return 红包数
     */
    int countLtPriceExtRedPackageByBeginTimeAndEndTimeAndType(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("type") String type, @Param("price") double price);

    /**
     * 更新用户红包过期时间
     *
     * @param customerId 客户id
     * @param id         红包id
     * @param expTime    过期时间
     * @return 0 1
     */
    int updateExtRedPackageExpTimeByCustomerIdAndId(@Param("customerId") int customerId, @Param("id") int id, @Param("expTime") Date expTime);

    /**
     * 根据红包id集合获取用户红包
     * @param customerId 用户
     * @param ids 红包id集合
     * @return 红包集合
     */
    List<ExtRedPackage> getEffectiveExtRedPackageByCustomerIdAndIds(@Param("customerId") int customerId, @Param("ids") List<Integer> ids);

    /**
     * 根据红包id集合更新红包已经使用
     * @param customerId 用户id
     * @param ids 红包id集合
     */
    void updateExtRedPackageIsUsedByCustomerIdAndIds(@Param("customerId") int customerId, @Param("ids") List<Integer> ids);

    /**
     * 分页用户有效红包
     * @param customerId 用户id
     * @param type 红包类型
     * @return
     */
    Page<ExtRedPackage> pageEffectiveExtRedPackageByCustomerIdOrderByType(@Param("customerId") int customerId, @Param("type")String type);

    /**
     * 用户所有有效红包
     * @param customerId 用户id
     * @param type 红包类型
     * @return
     */
    List<ExtRedPackage> getByCustomerIdOrderByType(@Param("customerId") int customerId, @Param("type")String type);

    /**
     * 分组红包列表
     * @param customerId 用户id
     * @param price 红包金额
     * @return
     */
    Page<ExtRedPackage> pageEffectiveByCustomerIdAndPrice(@Param("customerId") int customerId, @Param("price")double price);

    /**
     * 过期红包列表
     * @param customerId 用户id
     * @param type 红包金额
     * @return
     */
    Page<ExtRedPackage> pageEffectiveByCustomerIdOrderByType(@Param("customerId") int customerId, @Param("type")String type);

    /**
     * 红包信息
     * @param id 红包id
     * @return
     */
    ExtRedPackage getById(@Param("id") int id);
}
