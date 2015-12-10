package com.ruanko.utils;

/**
 * 自定义异常处理类
 * 
 * @author rkcoe
 * 
 */
public class AppException extends Exception {
	private int exceptionCode; // 异常编号
	private String message; // 异常信息

	/*
	 * 构造方法 初始化返回异常的信息
	 */
	public AppException(String message) {
		this.message = message;
	}

	/*
	 * 构造方法 初始化返回异常的信息和异常编号
	 */
	public AppException(int exceptionCode, String message) {
		this.exceptionCode = exceptionCode;
		this.message = message;
	}

	/*
	 * 返回详细的异常信息
	 */
	public String getDetailMessage() {
		String detailMessage = "Detail Message:" + exceptionCode + ":"
				+ message;// 详细的异常信息
		return detailMessage;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
