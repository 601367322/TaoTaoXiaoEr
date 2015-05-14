package com.ttxr.fragment;

import android.os.Handler;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.MessageAdapter;
import com.ttxr.bean.OrderHistoryBean;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.shen on 2015/4/25.
 */
@EFragment(R.layout.fragment_list)
public class MessageFragment extends BaseListFragment {

    @Override
    public int getFragmentTitle() {
        return R.string.message_center;
    }

    @Override
    public void afterViews() {
        super.afterViews();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<OrderHistoryBean> list = new ArrayList<>();
//                for (int i = 0; i < 10; i++) {
//                    list.add(new OrderHistoryBean());
//                }
                adapter.setList(list);

                refreshLayout.setRefreshing(false);
                listview.setPage(++page);
            }
        }, 1000);

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public int getEmptyView() {
        return R.layout.message_empty_view;
    }

    @Override
    public BaseAdapter getAdapter() {
        return new MessageAdapter(getActivity());
    }
}
