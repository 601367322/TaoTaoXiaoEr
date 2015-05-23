package com.ttxr.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.adapter.OrderHistoryAdapter;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.AnimUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by mr.shen on 2015/4/25.
 */
@EFragment(R.layout.fragment_order)
public class OrderHistoryFragment extends BaseFragment implements IFragmentTitle {

    @ViewById
    View time_tab, time_select_item, time_select_item_content, trans_bg, time_state_icon;
    int page = 0;

    OrderHistoryAdapter adapter;

    @Override
    public int getFragmentTitle() {
        return R.string.my_order;
    }

    @Override
    public void afterViews() {
        getChildFragmentManager().beginTransaction().replace(R.id.list_content, new OrderHistoryListFragment_()).commitAllowingStateLoss();
    }


    @Click
    public void trans_bg() {
        closeTimeTab();
    }

    @Click
    public void time_tab() {
        int state = time_select_item.getVisibility();
        time_select_item_content.measure(0, 0);
        if (state == View.GONE) {
            time_select_item.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(trans_bg, AnimUtil.ALPHA, 0f, 1f).setDuration(200).start();
            ObjectAnimator.ofFloat(time_state_icon, AnimUtil.ROTATION, 0f, -180f).setDuration(200).start();
            ObjectAnimator.ofFloat(time_select_item_content, AnimUtil.TRANSLATIONY, -time_select_item_content.getMeasuredHeight(), 0).setDuration(200).start();
        } else {
            closeTimeTab();
        }
    }

    public void closeTimeTab() {
        ValueAnimator animator = ObjectAnimator.ofFloat(trans_bg, "alpha", 1f, 0f).setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                time_select_item.setVisibility(View.GONE);
            }
        });
        animator.start();
        ObjectAnimator.ofFloat(time_state_icon, AnimUtil.ROTATION, -180f, 0f).setDuration(200).start();
        ObjectAnimator.ofFloat(time_select_item_content, AnimUtil.TRANSLATIONY, 0, -time_select_item_content.getMeasuredHeight()).setDuration(200).start();
    }

}
