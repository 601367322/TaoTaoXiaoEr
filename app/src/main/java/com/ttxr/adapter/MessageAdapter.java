package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseHolder;
import com.ttxr.bean.UserMsg;

import butterknife.FindView;

/**
 * Created by sbb on 2015/5/14.
 */
public class MessageAdapter extends BaseAdapter<UserMsg> {


    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = getConvertView(R.layout.message_fragment_list_item);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserMsg msg = getItem(position);
        if (msg != null) {
            holder.text.setText(msg.getMsgContent());
            holder.time.setText(msg.getDateStr());
            holder.title.setText(msg.getMsgTitle());
        }
        return convertView;
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
    }
}
