package com.ruanko.webservice;

import javax.jws.WebService;

import com.ruanko.utils.AppException;

@WebService
public interface BcBankWebService
{
	/**
	 * 传入账号 密码 合作ID 支付账号  支付金额 进行付款
	 * @param name用户银行账号
	 * @param password用户银行密码
	 * @param appId用户合作ID
	 * @param recv_account支付账号
	 * @param money支付金额
	 * @return
	 * @throws AppException
	 */
	int pay(String name,String password,String appId,String recv_account,float money) throws AppException;
}
