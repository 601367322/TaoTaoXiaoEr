package com.ttxr.activity.base;

import android.content.Context;

import java.util.List;

/**
 * Created by mr.shen on 2015/4/19.
 */
public abstract class BaseAdapter<E> extends android.widget.BaseAdapter {

    public List<E> list;
    public Context context;

    public E getItem(int position) {
        if (list != null && list.size() > 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    public void setList(List<E> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<E> getList() {
        return list;
    }

    public BaseAdapter(List<E> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
