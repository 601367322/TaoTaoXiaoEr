package com.ttxr.activity.base;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

/**
 * Created by mr.shen on 2015/5/16.
 */
@EActivity
public abstract class BaseBackActivity extends BaseActivity{


    @OptionsItem(android.R.id.home)
    public void homeClick() {
        finish();
    }
}
