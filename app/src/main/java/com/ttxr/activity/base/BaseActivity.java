package com.ttxr.activity.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by mr.shen on 2015/4/18.
 */
@EActivity
public abstract class BaseActivity extends ActionBarActivity {

    @AfterViews
    public void afterViews() {
    }

    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commitAllowingStateLoss();
    }
}
