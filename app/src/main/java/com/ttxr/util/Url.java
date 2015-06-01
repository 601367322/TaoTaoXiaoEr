package com.ttxr.util;

/**
 * Created by mr.shen on 2015/5/16.
 */
public class Url {

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    public String HOST = "http://192.168.0.100:8080/AppComp/";
    public String PORT = "8080";

    public static final String REQUEST_DATA = "requestdata";
    public static final String RET_CODE = "retCode";
    public static final String RET_MESSAGE = "retMessage";

    private static Url instance;

    private Url() {
    }

    private Url(String url) {
        this.HOST = url;
    }

    public static synchronized Url getInstance() {
        if (instance == null) {
            instance = new Url();
        }
        return instance;
    }

    public static synchronized Url getInstance(String str) {
        instance = new Url(str);
        return instance;
    }

    public String getURL(String url){
        return getHOST() + url;
    }


    //注册
    public static String REG = "appservice/appUser/register";
    //登录
    public static String LOGIN = "appservice/login/getLogin";
    //退出
    public static String EXIT = "appservice/login/getLogOut";
    //保存用户信息
    public static String UPDATE_USERINFO = "appservice/appUser/updateAppUser";
    //保存头像
    public static String UPDATE_LOGO = "appservice/appUser/uploadFile";
    //消息列表
    public static String MESSAGE_LIST = "appservice/userMsg/getUserMsgList";
    //获取订单请求
    public static String GET_ORDER_REQUEST = "appservice/appOrder/getMyOrderRequest";
    //获取订单信息
    public static String GET_MY_ORDER = "appservice/appOrder/getMyAppOrder";
    //改变订单状态
    public static String CHANGE_ORDER_STATUS = "appservice/appOrder/sendAppOrder";
    //获取接单记录
    public static String GET_MY_ORDER_LIST = "appservice/appOrder/getMyOrderList";
    //获取订单状态
    public static String GET_ORDER_STATUS = "appservice/appOrder/getOrderStatus";

}
