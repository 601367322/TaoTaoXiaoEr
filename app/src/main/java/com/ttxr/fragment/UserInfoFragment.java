package com.ttxr.fragment;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.interfaces.IFragmentTitle;

import org.androidannotations.annotations.EFragment;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EFragment(R.layout.fragment_userinfo)
public class UserInfoFragment extends BaseFragment implements IFragmentTitle{

    @Override
    public int getFragmentTitle() {
        return R.string.userinfo;
    }
}
