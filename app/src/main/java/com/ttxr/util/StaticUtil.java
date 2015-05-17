package com.ttxr.util;

import android.os.Environment;

/**
 * Created by mr.shen on 2015/5/17.
 */
public class StaticUtil {

    public static final String SDCardPath = Environment.getExternalStorageDirectory().getPath();//SD卡地址
    public static final String APKCardPath = Environment.getExternalStorageDirectory().getPath() + "/ttxr/";//SD卡地址
    public static final String APKCardPathImg = APKCardPath + "img/";//
}
