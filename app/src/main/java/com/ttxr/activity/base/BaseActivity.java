package com.ttxr.activity.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ttxr.application.AppClass;

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

    @AfterViews
    public void afterViews() {
    }

    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(id, fragment).commitAllowingStateLoss();
    }
}
