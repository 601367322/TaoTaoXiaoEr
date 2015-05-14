package com.ttxr.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Switch;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.share.CommonShared;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by mr.shen on 2015/4/25.
 */
@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment implements IFragmentTitle {

    @ViewById
    TextView versionName;
    @ViewById
    Switch push_switch;

    @Override
    public void afterViews() {
        versionName.setText("当前版本：" + getAppVersionName(getActivity()));

        int switcher = ac.cs.getPushSwitch();
        if(switcher == CommonShared.ON){
            push_switch.setChecked(true);
        }else{
            push_switch.setChecked(false);
        }
    }

    @Override
    public int getFragmentTitle() {
        return R.string.setting;
    }

    @Click
    public void umeng_fb() {
        FeedbackAgent agent = new FeedbackAgent(getActivity());
        agent.sync();
        agent.openAudioFeedback();
        agent.openFeedbackPush();
        agent.startFeedbackActivity();
    }

    @Click
    public void check_version() {
        //umeng 更新
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                if (updateResponse != null && !updateResponse.hasUpdate) {
                    toast("已经是最新版本了!");
                }
            }
        });
        UmengUpdateAgent.update(getActivity());
    }

    @CheckedChange
    public void push_switch(boolean isCheck){
        if(isCheck){
            ac.cs.setPushSwitch(CommonShared.ON);
            PushAgent.getInstance(getActivity()).enable();
        }else{
            PushAgent.getInstance(getActivity()).disable();
            ac.cs.setPushSwitch(CommonShared.OFF);
        }
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }
}
