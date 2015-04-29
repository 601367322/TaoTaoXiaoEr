package com.ttxr.share;

import android.content.Context;

public class CommonShared {
    private SharedDataUtil sp;
    private SharedDataUtil.SharedDataEditor editor;
    public static final int OFF = 0;
    public static final int ON = 1;

    private final String HAVE_WELCOME = "have_welcome_v1";

    public CommonShared(Context context) {
        sp = SharedDataUtil.getInstance(context);
        editor = sp.getSharedDataEditor();
    }

    public void setHaveWelcome(int state) {
        editor.putInt(HAVE_WELCOME, state);
        editor.commit();
    }

    public int getHaveWelcome() {
        return sp.getInt(HAVE_WELCOME, OFF);
    }

}
