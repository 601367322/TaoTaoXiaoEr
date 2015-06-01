package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.bean.AppOrder;

import butterknife.FindView;

/**
 * Created by mr.shen on 2015/4/25.
 */
public class OrderHistoryAdapter extends BaseAdapter<AppOrder> {

    public OrderHistoryAdapter(Context context) {
        super(context);
    }

    class ViewHolder extends BaseHolder {
        @FindView(R.id.name)
        TextView name;
        @FindView(R.id.price)
        TextView price;
        @FindView(R.id.address)
        TextView address;
        @FindView(R.id.time)
        TextView time;
        @FindView(R.id.status)
        TextView status;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind(AppOrder bean) {
            name.setText(bean.getStoreName());
            price.setText("￥"+bean.getDeliverPrice());
            address.setText(bean.getPerAddress());
            time.setText(bean.getDateStr());
            status.setText(bean.getStatusStr());
        }
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getConvertView() {
        return R.layout.order_history_fragment_list_item;
    }
}
