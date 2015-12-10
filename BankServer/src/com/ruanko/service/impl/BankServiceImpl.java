package com.ruanko.service.impl;

import com.ruanko.dao.BankDao;
import com.ruanko.dao.impl.BankDaoImpl;
import com.ruanko.model.Bank;
import com.ruanko.service.BankService;
import com.ruanko.utils.AppException;

public class BankServiceImpl implements BankService {
	private BankDao bankDao = new BankDaoImpl();

	/**
	 * 
	 * 验证是否存在此用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public Bank getBankUserInfo(String name, String password)
			throws AppException {
		Bank bank = bankDao.getBankUserInfo(name, password);
		return bank;
	}

	/**
	 * 判断支付账户是否是银行合作伙伴
	 * 
	 * @param recv_account
	 * @param appId
	 * @return
	 * @throws AppException
	 */
	public Bank isPartner(String recv_account, String appId)
			throws AppException {
		Bank bank = bankDao.isPartner(recv_account, appId);
		return bank;
	}

	/**
	 * 进行支付功能
	 * 
	 * @param bank
	 * @param recv_bank
	 * @param money
	 * @return
	 * @throws AppException
	 */
	public int pay(Bank bank, Bank recv_bank, float money) throws AppException {
		int result =30;
		result=bankDao.pay(bank,recv_bank,money);
		return result;
	}

}
