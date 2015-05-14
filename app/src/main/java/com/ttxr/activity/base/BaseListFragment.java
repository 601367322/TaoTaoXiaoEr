package com.ttxr.activity.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttxr.activity.R;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.weight.listview.XListView;
import com.ttxr.weight.swipe.SwipeRefreshLayout;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbb on 2015/5/14.
 */
@EFragment(R.layout.fragment_list)
public abstract class BaseListFragment<T> extends BaseFragment implements IFragmentTitle, SwipeRefreshLayout.OnRefreshListener, XListView.IXListViewListener {

    @ViewById
    public XListView listview;
    @ViewById
    public SwipeRefreshLayout refreshLayout;

    public BaseAdapter<T> adapter;

    public View emptyView;

    public int page = 0;

    @Override
    public void afterViews() {

        if (getEmptyView() != -1) {
            emptyView = LayoutInflater.from(getActivity()).inflate(getEmptyView(), (ViewGroup) listview.getParent(), false);
            ((ViewGroup) listview.getParent()).addView(emptyView);
            listview.setEmptyView(emptyView);
        }

        listview.setXListViewListener(this);

        listview.setAdapter(adapter = getAdapter());

        refreshLayout.setOnRefreshListener(this);
        refresh();
    }

    @UiThread
    public void refresh() {
        refreshLayout.setRefreshing(true);
    }

    public int getEmptyView() {
        return -1;
    }

    public abstract BaseAdapter<T> getAdapter();

}
