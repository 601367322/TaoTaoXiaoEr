package com.ttxr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.bean.MenuBean;

import butterknife.ButterKnife;
import butterknife.FindView;

/**
 * Created by mr.shen on 2015/4/19.
 */
public class MenuAdapter extends BaseAdapter<MenuBean> {

    public MenuAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_left_menu_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MenuBean bean = getItem(position);
        holder.icon.setImageResource(bean.icon);
        holder.title.setText(bean.title);
        if (bean.badge) {
            holder.badge.setVisibility(View.VISIBLE);
        } else {
            holder.badge.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {

        @FindView(R.id.icon)
        ImageView icon;
        @FindView(R.id.badge)
        ImageView badge;
        @FindView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
