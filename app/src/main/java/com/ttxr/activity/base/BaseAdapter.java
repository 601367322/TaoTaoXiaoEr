package com.ttxr.activity.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

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

    public void add(List<E> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public BaseAdapter(Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getConvertView(), parent, false);
            holder = getHolder(convertView);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.bind(getItem(position));
        return convertView;
    }

    public abstract BaseHolder getHolder(View view);

    public abstract int getConvertView();

    public abstract class BaseHolder {

        public BaseHolder(View view) {

            ButterKnife.bind(this, view);

            view.setTag(this);
        }

        public abstract void bind(E bean);
    }

}
