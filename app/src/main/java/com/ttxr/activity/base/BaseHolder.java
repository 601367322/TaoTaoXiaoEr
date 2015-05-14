package com.ttxr.activity.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by sbb on 2015/5/14.
 */
public abstract class BaseHolder {

    public BaseHolder(View view) {

        ButterKnife.bind(this, view);

        view.setTag(this);
    }
}
