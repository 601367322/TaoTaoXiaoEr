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

}
