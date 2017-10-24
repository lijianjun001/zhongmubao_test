package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtActivityRecord;

/**
 * 礼物领取数据层
 *
 * @author 孙阿龙
 */
public interface ExtActivityRecordDao {
	/**
	 * 添加领取礼物
	 * @param extActivityRecord 实体
	 * @return 0 1
	 */
	int insertExtActivityRecord(ExtActivityRecord extActivityRecord);
}
