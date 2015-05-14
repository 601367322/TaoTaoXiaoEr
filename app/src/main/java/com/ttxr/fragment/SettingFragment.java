package com.ttxr.fragment;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.interfaces.IFragmentTitle;
import com.umeng.fb.FeedbackAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by mr.shen on 2015/4/25.
 */
@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment implements IFragmentTitle {
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
}
