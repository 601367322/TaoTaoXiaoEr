package com.ttxr.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
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
    View time_tab, time_select_item, time_select_item_content, status_tab, status_select_item_content, trans_bg, time_state_icon, status_state_icon;

    private String orderByStr = "desc";
    private String statusStr = null;

    @Override
    public int getFragmentTitle() {
        return R.string.my_order;
    }

    @Override
    public void afterViews() {
        getChildFragmentManager().beginTransaction().replace(R.id.list_content, OrderHistoryListFragment_.builder().orderByStr(orderByStr).statusStr(statusStr).build()).commitAllowingStateLoss();
    }


    @Click
    public void trans_bg() {
        closeTimeTab(time_select_item_content.getVisibility() == View.GONE ? status_select_item_content : time_select_item_content, time_select_item_content.getVisibility() == View.GONE ? status_state_icon : time_state_icon);
    }

    @Click
    public void time_tab(View view) {
        view.setBackgroundResource(R.drawable.order_tab_press);
        if (status_select_item_content.getVisibility() == View.VISIBLE) {
            trans_bg();
            return;
        }
        show_tab(time_select_item_content, time_state_icon);
    }

    public void closeTimeTab(final View view, View icon) {
        ValueAnimator animator = ObjectAnimator.ofFloat(trans_bg, AnimUtil.ALPHA, 1f, 0f).setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                time_select_item.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                status_tab.setBackgroundResource(R.drawable.order_tab_normal);
                time_tab.setBackgroundResource(R.drawable.order_tab_normal);

            }
        });
        animator.start();
        ObjectAnimator.ofFloat(icon, AnimUtil.ROTATION, -180f, 0f).setDuration(200).start();
        ObjectAnimator.ofFloat(view, AnimUtil.TRANSLATIONY, 0, -view.getMeasuredHeight()).setDuration(200).start();
    }

    public void show_tab(View view, View icon) {
        int state = time_select_item.getVisibility();
        view.measure(0, 0);
        if (state == View.GONE) {
            time_select_item.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(trans_bg, AnimUtil.ALPHA, 0f, 1f).setDuration(200).start();
            ObjectAnimator.ofFloat(icon, AnimUtil.ROTATION, 0f, -180f).setDuration(200).start();
            ObjectAnimator.ofFloat(view, AnimUtil.TRANSLATIONY, -view.getMeasuredHeight(), 0).setDuration(200).start();
        } else {
            closeTimeTab(view, icon);
        }
    }

    @Click
    public void status_tab(View view) {
        view.setBackgroundResource(R.drawable.order_tab_press);
        if (time_select_item_content.getVisibility() == View.VISIBLE) {
            trans_bg();
            return;
        }
        show_tab(status_select_item_content, status_state_icon);
    }

    @Click({R.id.time_desc, R.id.time_esc, R.id.status1, R.id.status2, R.id.status3, R.id.status4, R.id.status5, R.id.status6})
    public void tabClick(View view) {
        trans_bg();
        LinearLayout group = (LinearLayout) view.getParent();
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = group.getChildAt(i);
            if (child instanceof RelativeLayout) {
                RelativeLayout parent = (RelativeLayout) child;
                TextView text = (TextView) parent.getChildAt(0);
                View icon = parent.getChildAt(1);
                text.setTextColor(getResources().getColor(R.color.setting_title));
                icon.setVisibility(View.GONE);
            }
        }
        RelativeLayout parent = (RelativeLayout) view;
        TextView text = (TextView) parent.getChildAt(0);
        View icon = parent.getChildAt(1);
        text.setTextColor(getResources().getColor(R.color.actionbar));
        icon.setVisibility(View.VISIBLE);
        OrderHistoryListFragment_.FragmentBuilder_ builder_ = OrderHistoryListFragment_.builder();
        switch (view.getId()) {
            case R.id.time_desc:
                orderByStr = "desc";
                break;
            case R.id.time_esc:
                orderByStr = "asc";
                break;
            case R.id.status1:
                statusStr = "1";
                break;
            case R.id.status2:
                statusStr = "2";
                break;
            case R.id.status3:
                statusStr = "3";
                break;
            case R.id.status4:
                statusStr = "4";
                break;
            case R.id.status5:
                statusStr = "5";
                break;
            case R.id.status6:
                statusStr = null;
                break;
        }
        builder_.orderByStr(orderByStr);
        builder_.statusStr(statusStr);
        getChildFragmentManager().beginTransaction().replace(R.id.list_content, builder_.build()).commitAllowingStateLoss();
    }


}
