package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.bean.OrderStatus;

import butterknife.FindView;

/**
 * Created by Shen on 2015/5/30.
 */
public class OrderStatusAdapter extends BaseAdapter<OrderStatus> {


    public OrderStatusAdapter(Context context) {
        super(context);
    }

    class ViewHolder extends BaseHolder {

        @FindView(R.id.logo)
        ImageView logo;
        @FindView(R.id.title)
        TextView title;
        @FindView(R.id.time)
        TextView time;
        @FindView(R.id.content)
        TextView content;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind(OrderStatus bean) {
            if(bean.getStatus().equals("2")){
                logo.setImageResource(R.drawable.state_home_icon);
            }else{
                logo.setImageResource(R.drawable.state_user_icon);
            }
            title.setText(bean.getTitle());
            content.setText(bean.getContent());
            time.setText(bean.getDateStr());
        }
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getConvertView() {
        return R.layout.adapter_order_detail_item;
    }
}
