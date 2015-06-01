package com.ttxr.bean.request_model;

public class MyOrderRequestDTO {
	
	private String orderId;    //订单id
	private String status;     //1接单 2商家确认 3取餐 4送餐 5完成
	private String token;      //令牌
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
