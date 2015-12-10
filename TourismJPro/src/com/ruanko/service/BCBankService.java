package com.ruanko.service;

import com.ruanko.webservice.AppException_Exception;
import com.ruanko.webservice.BcBankWebService;
import com.ruanko.webserviceimpl.BcBankWebServiceImpl;

/**
 * 中国银行WebService调用实现
 * 
 * @author rkcoe
 * 
 */
public class BCBankService {
	private static String BANKUSERNAME = "1";// 银行卡账号
	private static String BANKUSERPASSWORD = "1";// 银行卡密码
	private static String APPID = "1001"; // 商户和以后合作ID 银行伙伴号

	/**
	 * 根据银行需要的请求参数进行支付
	 * 
	 * @param money
	 * @param name用户账号
	 * @param password用户密码
	 * @param appId合作ID
	 * @param recv_account
	 * @return
	 */
	public int payByNameAndPassword(float money, String name, String password,
			String appId, String recv_account) {
		// 调用银行的webservice的pay支付方法
		// 传入账号 密码 合作ID 支付账号 支付金额
		int result = 30; 

		BcBankWebServiceImpl bcBankWebServiceImpl = new BcBankWebServiceImpl();
		BcBankWebService bankService = bcBankWebServiceImpl
				.getBcBankWebServiceImplPort();
		try {  
			result = bankService
					.pay(name, password, appId, recv_account, money);
		} catch (AppException_Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
