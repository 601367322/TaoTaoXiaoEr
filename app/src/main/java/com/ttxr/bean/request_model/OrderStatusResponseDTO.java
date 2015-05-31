package com.ttxr.bean.request_model;

import com.ttxr.bean.OrderStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusResponseDTO implements Serializable {
	
	private Integer retCode;  //0 成功
	private String retMessage;
	private String orderNum;  //订单编号
	private String storeName; //餐厅名称
	private String storePhone; //餐厅的电话
	private String perPhone;   //订餐人的电话
	private List<OrderStatus> statusList = new ArrayList<OrderStatus>();  //该订单各个状态的描述
	
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public String getPerPhone() {
		return perPhone;
	}
	public void setPerPhone(String perPhone) {
		this.perPhone = perPhone;
	}
	public List<OrderStatus> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<OrderStatus> statusList) {
		this.statusList = statusList;
	}
}
