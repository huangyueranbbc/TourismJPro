package com.ruanko.dao;

import com.ruanko.model.Bank;
import com.ruanko.utils.AppException;

/**
 * 银行系统的Dao接口
 * 
 * @author rkcoe
 * 
 */
public interface BankDao {

	/**
	 * 是否存在此银行用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	Bank getBankUserInfo(String name, String password) throws AppException;

	/**
	 * 判断是否是银行伙伴账号 如果是 则返回该账户数据
	 * 
	 * @param recv_account
	 * @param appId
	 * @return
	 * @throws AppException
	 */
	Bank isPartner(String recv_account, String appId) throws AppException;

	/**
	 * 根据付款账号和支付账号进行付款
	 * 
	 * @param bank
	 * @param recv_bank
	 * @param money
	 * @return
	 * @throws AppException
	 */
	int pay(Bank bank, Bank recv_bank, float money) throws AppException;

}
