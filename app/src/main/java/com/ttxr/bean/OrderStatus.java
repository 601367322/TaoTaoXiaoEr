package com.ttxr.bean;

import java.io.Serializable;

public class OrderStatus implements Serializable {
	private String title;   //状态标题
	private String content; //状态描述
	private String dateStr; //时间描述
	private String status;  //0为初始状态 1为接单状态 2为商家确认状态 3为取餐状态 4为送餐状态 5为订单完成状态 6已评价
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
