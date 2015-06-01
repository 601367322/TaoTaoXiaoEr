package com.ttxr.bean.request_model;

import android.content.Context;

import com.ttxr.activity.R;

public class MyOrderResponseDTO {
	
	private Integer retCode;//0 成功
	private String retMessage;
	private String orderId;    //订单id
	private String storeName;  //餐厅名称
	private String deliverPrice;  //配送费
	private String perAddress;    //订餐人地址
	private int status;        //订单状态    0为初始状态 1为接单状态 2为商家确认状态 3为取餐状态 4为送餐状态 5为订单完成状态 6已评价
	private String statusStr;     //订单状态描述
	private String dateStr;       //时间显示
	private String deliverHour;   //配送时间
	private String perPhone;      //订餐人电话
	private String storeLatitude;
	private String storeLongitude;

	public String getStatusBtnStr(Context context){
		switch (status){
			case 0:
				return context.getString(R.string.status1);
			case 1:
				return context.getString(R.string.status2);
			case 2:
				return context.getString(R.string.status3);
			case 3:
				return context.getString(R.string.status4);
			case 4:
				return context.getString(R.string.status5);
			case 5:
				return context.getString(R.string.status5);
			case 6:
				return context.getString(R.string.status7);
		}
		return "";
	}
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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

	public String getStoreLatitude() {
		return storeLatitude;
	}

	public void setStoreLatitude(String storeLatitude) {
		this.storeLatitude = storeLatitude;
	}

	public String getStoreLongitude() {
		return storeLongitude;
	}

	public void setStoreLongitude(String storeLongitude) {
		this.storeLongitude = storeLongitude;
	}

	public void setPerPhone(String perPhone) {
		this.perPhone = perPhone;
	}
}
