package com.ruanko.webserviceImpl;

import javax.jws.WebService;

import com.ruanko.dao.BankDao;
import com.ruanko.dao.impl.BankDaoImpl;
import com.ruanko.model.Bank;
import com.ruanko.service.BankService;
import com.ruanko.service.impl.BankServiceImpl;
import com.ruanko.utils.AppException;
import com.ruanko.webservice.BcBankWebService;

@WebService(endpointInterface = "com.ruanko.webservice.BcBankWebService", serviceName = "bcBankWebServiceImpl")
public class BcBankWebServiceImpl implements BcBankWebService
{
	private BankService bankService=new BankServiceImpl();

	@Override
	public int pay(String name, String password, String appId,
			String recv_account, float money) throws AppException {
		System.out.println("执行银行WEBSERVICE"); 
		int result=30;//支付结果类型 20成功 30失败
		//1进行判断 是否存在账户 并且支付的账号是否是合作ID
		Bank bank = bankService.getBankUserInfo(name,password);//是否存在此用户
		//如果存在此用户
		if(bank != null){
			//判断支付账号是否是银行伙伴用户
			Bank recv_bank = bankService.isPartner(recv_account,appId);//支付账号是否是银行伙伴ID
			//该支付账户是银行合作伙伴 全部验证结束 进行支付操作
			if(recv_bank!=null){
				//事务开启
				result=bankService.pay(bank,recv_bank,money);
				//事务结束
			}else {
				result=30;
			}
		}else {
			result=30;
		}
		
		//返回支付结果
		return result;
	} 


}
