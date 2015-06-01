package com.ttxr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.bean.MenuBean;

import butterknife.FindView;

/**
 * Created by mr.shen on 2015/4/19.
 */
public class MenuAdapter extends BaseAdapter<MenuBean> {

    public MenuAdapter(Context context) {
        super(context);
    }

    class ViewHolder extends BaseHolder{

        @FindView(R.id.icon)
        ImageView icon;
        @FindView(R.id.badge)
        ImageView badge;
        @FindView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind(MenuBean bean) {
            icon.setImageResource(bean.icon);
            title.setText(bean.title);
            if (bean.badge) {
                badge.setVisibility(View.VISIBLE);
            } else {
                badge.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getConvertView() {
        return R.layout.fragment_left_menu_list_item;
    }
}
