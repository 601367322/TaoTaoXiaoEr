package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseHolder;
import com.ttxr.bean.OrderHistoryBean;

/**
 * Created by mr.shen on 2015/4/25.
 */
public class OrderHistoryAdapter extends BaseAdapter<OrderHistoryBean> {

    public OrderHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = getConvertView(R.layout.order_history_fragment_list_item);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder extends BaseHolder{

        public ViewHolder(View view) {
            super(view);
        }
    }
}
