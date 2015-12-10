package com.ruanko.server;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;

import com.ruanko.webservice.BcBankWebService;
import com.ruanko.webserviceImpl.BcBankWebServiceImpl;

public class ServerMain {
	public static void main(String[] args) {
		BcBankWebService bankWebService = new BcBankWebServiceImpl();
		// 调用Endpoint的方法发布Web Service
		EndpointImpl publish = (EndpointImpl) Endpoint.publish(
				"http://127.0.0.1/bcWebservice", bankWebService);
		publish.getInInterceptors().add(new LoggingInInterceptor());
		publish.getOutInterceptors().add(new LoggingInInterceptor());
		// Endpoint publish = Endpoint.publish("http://127.0.0.1/webservice",
		// helloWebService);
		System.out.println("Web Service 发布成功");
	} 
} 
 