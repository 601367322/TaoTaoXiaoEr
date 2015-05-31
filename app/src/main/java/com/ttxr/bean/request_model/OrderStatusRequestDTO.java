package com.ttxr.bean.request_model;

public class OrderStatusRequestDTO extends BaseRequestDTO {
	private String orderId;  //订单id
	private String token;    //令牌

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
