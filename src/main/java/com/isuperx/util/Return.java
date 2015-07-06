package com.isuperx.util;

import org.springframework.http.HttpStatus;

public class Return {
	private boolean success;//请求api是否成功
	private HttpStatus statusCode;	//http状态码
	private String message;	//接口返回的附加消息
	private Object data;	//api返回数据
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
