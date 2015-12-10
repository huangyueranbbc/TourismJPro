package com.ruanko.service;

import com.ruanko.model.Bank;
import com.ruanko.utils.AppException;

public interface BankService {
	/**
	 * 
	 * 验证是否存在此银行用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	Bank getBankUserInfo(String name, String password) throws AppException;

	/**
	 * 判断支付账户是否是银行合作伙伴
	 * 
	 * @param recv_account
	 * @param appId
	 * @return
	 * @throws AppException
	 */
	Bank isPartner(String recv_account, String appId) throws AppException;

	/**
	 * 进行支付功能
	 * 
	 * @param bank
	 * @param recv_bank
	 * @param money
	 * @return
	 * @throws AppException
	 */
	int pay(Bank bank, Bank recv_bank, float money) throws AppException;

}
