package com.ttxr.bean.request_model;

public class OrderListRequestDTO extends BaseRequestDTO {
	private String timeSort;	//按时间排序， desc降序  asc升序  当是时间排序时，statusSort为null
	private String statusSort;  //按状态排序， desc降序  asc升序  当是状态排序时，timeSort为null
	private String token;       //令牌
	
	public String getTimeSort() {
		return timeSort;
	}
	public void setTimeSort(String timeSort) {
		this.timeSort = timeSort;
	}
	public String getStatusSort() {
		return statusSort;
	}
	public void setStatusSort(String statusSort) {
		this.statusSort = statusSort;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
