package com.ttxr.util;

/**
 * Created by mr.shen on 2015/5/16.
 */
public class Url {

    public static String HOST = "http://192.168.0.100:8080/AppComp/";
    public static String PORT = "8080";

    public static final String REQUEST_DATA = "requestdata";
    public static final String RET_CODE = "retCode";
    public static final String RET_MESSAGE = "retMessage";

    //注册
    public static String REG = HOST + "appservice/appUser/register";
    //登录
    public static String LOGIN = HOST + "appservice/login/getLogin";
    //退出
    public static String EXIT = HOST + "appservice/login/getLogOut";
    //保存用户信息
    public static String UPDATE_USERINFO = HOST + "appservice/appUser/updateAppUser";
    //保存头像
    public static String UPDATE_LOGO = HOST + "appservice/appUser/uploadFile";
    //消息列表
    public static String MESSAGE_LIST = HOST + "appservice/userMsg/getUserMsgList";
    //获取订单请求
    public static String GET_ORDER_REQUEST = HOST + "appservice/appOrder/getMyOrderRequest";
    //获取订单信息
    public static String GET_MY_ORDER = HOST + "appservice/appOrder/getMyAppOrder";

}
