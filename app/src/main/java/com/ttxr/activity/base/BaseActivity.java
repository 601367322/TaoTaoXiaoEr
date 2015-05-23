package com.ttxr.activity.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ttxr.application.AM;
import com.ttxr.application.AppClass;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

/**
 * Created by mr.shen on 2015/4/18.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @App
    public AppClass ac;

    public Context context;

    @AfterViews
    public void afterViews() {
    }

    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(id, fragment).commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(getApplicationContext()).onAppStart();
//        if(destoryPop()) {
            AM.getActivityManager().pushActivity(this);
//        }
        context = this;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(destoryPop()) {
            AM.getActivityManager().popActivity(this);
//        }
    }

    public boolean destoryPop(){
        return true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
