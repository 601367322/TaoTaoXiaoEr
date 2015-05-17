package com.ttxr.activity;

import android.os.Bundle;

import com.ttxr.activity.base.BaseActivity;
import com.ttxr.activity.user.LoginActivity_;
import com.ttxr.activity.user.RegActivity_;
import com.ttxr.location.GDLocation;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by sbb on 2015/4/29.
 */
@EActivity(R.layout.activity_login_and_reg)
public class LoginAndRegActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        new GDLocation(this,null,true);
    }

    @Click
    public void toLogin(){
        LoginActivity_.intent(this).start();
    }

    @Click
    public void toReg(){
        RegActivity_.intent(this).start();
    }
}
