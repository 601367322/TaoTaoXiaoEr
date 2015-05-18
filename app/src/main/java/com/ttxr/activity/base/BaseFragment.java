package com.ttxr.activity.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.ttxr.application.AppClass;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EFragment
public abstract class BaseFragment extends Fragment {

    @App
    public AppClass ac;

    @AfterViews
    public void afterViews() {
        if (getActivity() == null) {
            return;
        }
    }

    Toast toast;

    public void toast(String str) {
        if (getActivity() != null) {
            toast = Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
