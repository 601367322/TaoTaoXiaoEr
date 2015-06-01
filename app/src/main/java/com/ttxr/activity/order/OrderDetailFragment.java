package com.ttxr.activity.order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseApi;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.OrderStatusAdapter;
import com.ttxr.api.OrderStatusApi;
import com.ttxr.bean.OrderStatus;
import com.ttxr.bean.request_model.OrderStatusResponseDTO;
import com.ttxr.util.Util;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.json.JSONObject;

/**
 * Created by Shen on 2015/5/30.
 */
@EFragment(R.layout.fragment_list)
public class OrderDetailFragment extends BaseListFragment<OrderStatus> {

    @FragmentArg
    String orderId;

    @Override
    public void afterViews() {
        super.afterViews();
        listview.setPadding(0, 0, 0, Util.dip2px(getActivity(), 64));
        listview.setClipToPadding(false);
        listview.setDivider(null);
        listview.setDividerHeight(Util.dip2px(getActivity(), 16));
        listview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listview.setFastScrollEnabled(false);
        listview.setVerticalScrollBarEnabled(false);
    }

    @Override
    public BaseAdapter getAdapter() {
        return new OrderStatusAdapter(getActivity());
    }

    @Override
    public BaseApi getApi() {
        return new OrderStatusApi(getActivity(), orderId);
    }

    @Override
    public String getJSONObjectListKey() {
        return "statusList";
    }

    @Override
    public Class<?> getClazz() {
        return OrderStatus.class;
    }

    @Override
    public int getFragmentTitle() {
        return 0;
    }

    @Override
    public boolean pageEnable() {
        return false;
    }

    @Override
    public void onSuccessCallBack(JSONObject jo) {
        OrderStatusResponseDTO bean = new Gson().fromJson(jo.toString(), new TypeToken<OrderStatusResponseDTO>() {
        }.getType());
        if (activity != null && bean != null) {
            ((OrderDetailActivity) activity).showBottom(bean);
        }
    }
}
