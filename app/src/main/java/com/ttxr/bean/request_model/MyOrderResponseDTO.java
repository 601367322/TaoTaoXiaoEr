package com.ttxr.bean.request_model;

public class MyOrderResponseDTO {
	
	private Integer retCode;//0 成功
	private String retMessage;
	private String orderId;    //订单id
	private String storeName;  //餐厅名称
	private String deliverPrice;  //配送费
	private String perAddress;    //订餐人地址
	private String status;        //订单状态    0为初始状态 1为接单状态 2为商家确认状态 3为取餐状态 4为送餐状态 5为订单完成状态 6已评价
	private String statusStr;     //订单状态描述
	private String dateStr;       //时间显示
	private String deliverHour;   //配送时间
	private String perPhone;      //订餐人电话
	
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getDeliverPrice() {
		return deliverPrice;
	}
	public void setDeliverPrice(String deliverPrice) {
		this.deliverPrice = deliverPrice;
	}
	public String getPerAddress() {
		return perAddress;
	}
	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDeliverHour() {
		return deliverHour;
	}
	public void setDeliverHour(String deliverHour) {
		this.deliverHour = deliverHour;
	}
	public String getPerPhone() {
		return perPhone;
	}
	public void setPerPhone(String perPhone) {
		this.perPhone = perPhone;
	}
}
