package com.ttxr.activity;

import android.app.Activity;
import android.os.Bundle;

import com.ttxr.application.AppClass;
import com.ttxr.share.CommonShared;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

/**
 * Created by sbb on 2015/4/29.
 */
@EActivity
public class WelcomeActivity extends Activity {

    @App
    AppClass ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (ac.cs.getHaveWelcome() == CommonShared.OFF) {
//            MainActivity_.intent(this).start();
//            finish();
//        } else if (ac.cs.getHaveWelcome() == CommonShared.ON) {
            ac.cs.setHaveWelcome(CommonShared.OFF);
            ViewPagerActivity_.intent(this).start();
            finish();
//        }
    }
}
