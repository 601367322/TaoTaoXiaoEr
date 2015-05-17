package com.ttxr.bean.request_model;

import java.io.Serializable;

public class RegisterRequestDTO implements Serializable{
	private String userAccount;
	private String userPassword;
	private String userLatitude;
	private String userLongitude;
	private String userMsgId;
	private String version;
	private String regSource;
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
	public String getUserLatitude() {
		return userLatitude;
	}
	public void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}
	public String getUserLongitude() {
		return userLongitude;
	}
	public void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}
	public String getUserMsgId() {
		return userMsgId;
	}
	public void setUserMsgId(String userMsgId) {
		this.userMsgId = userMsgId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRegSource() {
		return regSource;
	}
	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}
}