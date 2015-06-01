package com.ttxr.share;

import android.content.Context;

import com.ttxr.util.Url;

public class CommonShared {
    private SharedDataUtil sp;
    private SharedDataUtil.SharedDataEditor editor;

    public static final int OFF = 0;
    public static final int ON = 1;

    private final String HAVE_WELCOME = "have_welcome_v1";
    private final String PUSH_SWITCH = "push_switch";
    private final String LAT = "lat";
    private final String LNG = "lng";


    private final String URL = "url";

    public CommonShared(Context context) {
        sp = SharedDataUtil.getInstance(context);
        editor = sp.getSharedDataEditor();
    }

    public void setHaveWelcome(int state) {
        editor.putInt(HAVE_WELCOME, state);
        editor.commit();
    }

    public int getHaveWelcome() {
        return sp.getInt(HAVE_WELCOME, ON);
    }

    public void setPushSwitch(int state) {
        editor.putInt(PUSH_SWITCH, state);
        editor.commit();
    }

    public int getPushSwitch() {
        return sp.getInt(PUSH_SWITCH, ON);
    }

    public String getLat() {
        return sp.getString(LAT, "");
    }

    public void setLat(String str) {
        editor.putString(LAT, str);
        editor.commit();
    }

    public String getLng() {
        return sp.getString(LNG, "");
    }

    public void setLng(String str) {
        editor.putString(LNG, str);
        editor.commit();
    }

    public String getUrl() {
        return sp.getString(URL, Url.HOST);
    }

    public void setUrl(String str) {
        editor.putString(URL, str);
        editor.commit();
    }
}
