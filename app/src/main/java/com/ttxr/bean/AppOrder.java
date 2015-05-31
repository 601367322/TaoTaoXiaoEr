package com.ttxr.bean;

import java.io.Serializable;

public class AppOrder implements Serializable {
	private String orderId;
	private String orderNum;    //订单编号
	private String userCode;	//送餐人code
	private String requestId;   //订单请求id
	private String perPoi;		//订餐人id
	private String perName;     //订餐人名字
	private String perLatitude; //订餐人纬度
	private String perLongitude; //订餐人经度
	private String perSex;		//订餐人性别
	private String perAddress;	//订餐人地址
	private String perPhone;	//订餐人电话
	private String storePoi;    //餐厅id
	private String storeName;	//餐厅名称
	private String storeLatitude; //餐厅纬度
	private String storeLongitude; //餐厅经度
	private String storeAddress; //餐厅地址
	private String storePhone;   //餐厅电话
	private String createDate;     //订单创建时间
	private String acceptOrderTime;  //接单时间
	private String confirmOrderTime;  //餐厅确认订单时间
	private String takeOrderTime;  //取餐时间
	private String receiveOrderTime; //送餐时间
	private String completeOrderTime;//订单完成时间
	private String totalPrice;	   //总价
	private String foodPrice;      //餐费
	private String amount;		   //总数
	private String deliverPrice;   //送餐费
	private String reducePrice;    //优惠金额
	private String paidPrice;      //实付金额
	private String deliverHour;    //配送时长
	private String status;    	   //订单状态    0为初始状态 1为接单状态 2为商家确认状态 3为取餐状态 4为送餐状态 5为订单完成状态  6为已评价
	private String broadcastId;    //广播id
	private String logisticsNum;   //物流单号
	private String dateStr;
	private String statusStr;

	public String getPerLongitude() {
		return perLongitude;
	}

	public void setPerLongitude(String perLongitude) {
		this.perLongitude = perLongitude;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getPerPoi() {
		return perPoi;
	}

	public void setPerPoi(String perPoi) {
		this.perPoi = perPoi;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerLatitude() {
		return perLatitude;
	}

	public void setPerLatitude(String perLatitude) {
		this.perLatitude = perLatitude;
	}

	public String getPerSex() {
		return perSex;
	}

	public void setPerSex(String perSex) {
		this.perSex = perSex;
	}

	public String getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}

	public String getPerPhone() {
		return perPhone;
	}

	public void setPerPhone(String perPhone) {
		this.perPhone = perPhone;
	}

	public String getStorePoi() {
		return storePoi;
	}

	public void setStorePoi(String storePoi) {
		this.storePoi = storePoi;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAcceptOrderTime() {
		return acceptOrderTime;
	}

	public void setAcceptOrderTime(String acceptOrderTime) {
		this.acceptOrderTime = acceptOrderTime;
	}

	public String getConfirmOrderTime() {
		return confirmOrderTime;
	}

	public void setConfirmOrderTime(String confirmOrderTime) {
		this.confirmOrderTime = confirmOrderTime;
	}

	public String getTakeOrderTime() {
		return takeOrderTime;
	}

	public void setTakeOrderTime(String takeOrderTime) {
		this.takeOrderTime = takeOrderTime;
	}

	public String getReceiveOrderTime() {
		return receiveOrderTime;
	}

	public void setReceiveOrderTime(String receiveOrderTime) {
		this.receiveOrderTime = receiveOrderTime;
	}

	public String getCompleteOrderTime() {
		return completeOrderTime;
	}

	public void setCompleteOrderTime(String completeOrderTime) {
		this.completeOrderTime = completeOrderTime;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDeliverPrice() {
		return deliverPrice;
	}

	public void setDeliverPrice(String deliverPrice) {
		this.deliverPrice = deliverPrice;
	}

	public String getReducePrice() {
		return reducePrice;
	}

	public void setReducePrice(String reducePrice) {
		this.reducePrice = reducePrice;
	}

	public String getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(String paidPrice) {
		this.paidPrice = paidPrice;
	}

	public String getDeliverHour() {
		return deliverHour;
	}

	public void setDeliverHour(String deliverHour) {
		this.deliverHour = deliverHour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	public String getLogisticsNum() {
		return logisticsNum;
	}

	public void setLogisticsNum(String logisticsNum) {
		this.logisticsNum = logisticsNum;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}
