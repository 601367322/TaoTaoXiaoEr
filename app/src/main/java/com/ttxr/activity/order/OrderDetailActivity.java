package com.ttxr.activity.order;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.bean.request_model.OrderStatusResponseDTO;
import com.ttxr.util.AnimUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Shen on 2015/5/30.
 */
@EActivity(R.layout.activity_order_detail)
public class OrderDetailActivity extends BaseBackActivity {

    @Extra
    String orderId;
    @ViewById
    TextView order_id;
    @ViewById
    View bottom_ll;

    OrderStatusResponseDTO bean;

    @Override
    public void afterViews() {
        super.afterViews();
        replaceFragment(R.id.content, OrderDetailFragment_.builder().orderId(orderId).build());
    }

    public void showBottom(OrderStatusResponseDTO bean) {
        this.bean = bean;
        if (order_id.getVisibility() == View.GONE) {
            order_id.setVisibility(View.VISIBLE);
            order_id.setText(String.format(getString(R.string.order_id_title), bean.getOrderNum()));
            bottom_ll.measure(0, 0);
            bottom_ll.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(bottom_ll, AnimUtil.TRANSLATIONY, bottom_ll.getMeasuredHeight(), 0).setDuration(200).start();
            getSupportActionBar().setTitle(bean.getStoreName());
        }
    }

    @Click
    public void call_1() {
        if (bean != null && !TextUtils.isEmpty(bean.getStorePhone())) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getStorePhone()));
            startActivity(intent);
        }
    }

    public void call_2() {
        if (bean != null && !TextUtils.isEmpty(bean.getPerPhone())) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getPerPhone()));
            startActivity(intent);
        }
    }
}
