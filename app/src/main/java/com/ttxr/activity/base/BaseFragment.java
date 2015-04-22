package com.ttxr.activity.base;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EFragment
public class BaseFragment extends Fragment {

    @AfterViews
    public void afterViews() {
    }
}
