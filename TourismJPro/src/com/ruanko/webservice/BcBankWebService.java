package com.ruanko.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.0
 * Tue Dec 08 15:39:03 CST 2015
 * Generated source version: 2.3.0
 * 
 */
 
@WebService(targetNamespace = "http://webservice.ruanko.com/", name = "BcBankWebService")
@XmlSeeAlso({ObjectFactory.class})
public interface BcBankWebService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "pay", targetNamespace = "http://webservice.ruanko.com/", className = "com.ruanko.webservice.Pay")
    @WebMethod
    @ResponseWrapper(localName = "payResponse", targetNamespace = "http://webservice.ruanko.com/", className = "com.ruanko.webservice.PayResponse")
    public int pay(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        float arg4
    ) throws AppException_Exception;
}
