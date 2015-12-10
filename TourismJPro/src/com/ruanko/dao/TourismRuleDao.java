package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.TourismRule;
import com.ruanko.utils.AppException;

/**
 * 旅游规则DAO
 * 
 * @author rkcoe
 * 
 */
public interface TourismRuleDao {
	/**
	 * 添加旅游规则信息
	 * 
	 * @param tourismRule
	 * @return
	 * @throws AppException
	 */
	boolean add(TourismRule tourismRule) throws AppException;

	/**
	 * 根据旅游信息ID获取所有的旅游规则
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException 
	 */
	List<TourismRule> getListByTourismId(int tourismId) throws AppException;

	/**
	 * 根据旅游规则ID返回旅游规则数据
	 * 
	 * @param tourismtimeRuleId
	 * @return
	 * @throws AppException
	 */
	TourismRule getTourismRule(int tourismtimeRuleId)throws AppException;
}
