package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.bean.UserMsg;

import butterknife.FindView;

/**
 * Created by sbb on 2015/5/14.
 */
public class MessageAdapter extends BaseAdapter<UserMsg> {


    public MessageAdapter(Context context) {
        super(context);
    }

    class ViewHolder extends BaseHolder {

        @FindView(R.id.time)
        TextView time;
        @FindView(R.id.text)
        TextView text;
        @FindView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind(UserMsg bean) {
            if (bean != null) {
                text.setText(bean.getMsgContent());
                time.setText(bean.getDateStr());
                title.setText(bean.getMsgTitle());
            }
        }
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getConvertView() {
        return R.layout.message_fragment_list_item;
    }
}
