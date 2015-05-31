package com.ttxr.fragment;

import android.app.Activity;

import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseApi;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.OrderHistoryAdapter;
import com.ttxr.api.OrderHistoryApi;
import com.ttxr.bean.AppOrder;
import com.ttxr.bean.UserMsg;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Shen on 2015/5/23.
 */
@EFragment(R.layout.fragment_list)
public class OrderHistoryListFragment extends BaseListFragment<AppOrder> {

    MainActivity_ activity_;

    @FragmentArg
    public String orderByStr;
    @FragmentArg
    public String statusStr;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity_ = (MainActivity_) activity;
    }

    @Override
    public BaseAdapter getAdapter() {
        return new OrderHistoryAdapter(getActivity());
    }

    @Override
    public int getFragmentTitle() {
        return 0;
    }

    @Override
    public BaseApi getApi() {
        return new OrderHistoryApi(getActivity(), orderByStr, statusStr);
    }

    @Override
    public String getJSONObjectListKey() {
        return "orderList";
    }

    @Override
    public void listview(int position) {
        AppOrder order = adapter.getItem(position);
        if (order != null) {
            if (activity_ != null) {
                activity_.startMapFragment(new UserMsg(order.getOrderId(), order.getStatus()));
            }
        }
    }

    @Override
    public Class<AppOrder> getClazz() {
        return AppOrder.class;
    }
}
