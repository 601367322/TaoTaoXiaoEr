package com.ttxr.bean.request_model;

public class UserMsgRequestDTO extends BaseRequestDTO{
	private String status;      //消息状态  0为未读 1已读 2删除 默认为null即全部
	private String msgType;     //消息类型 0为订单  默认为null即全部

	private String token;       //令牌
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
