package com.ttxr.bean.request_model;

import java.io.Serializable;

public class LoginRequestDTO implements Serializable{
	private String userAccount;
	private String userPassword;
	private String userMsgId;
	private String latitude;
	private String longitude;
	
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserMsgId() {
		return userMsgId;
	}
	public void setUserMsgId(String userMsgId) {
		this.userMsgId = userMsgId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
