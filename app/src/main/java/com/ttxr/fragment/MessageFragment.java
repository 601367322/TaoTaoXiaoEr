package com.ttxr.fragment;

import android.app.Activity;

import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseApi;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.MessageAdapter;
import com.ttxr.api.MessageApi;
import com.ttxr.bean.UserMsg;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

/**
 * Created by mr.shen on 2015/4/25.
 */
@EFragment(R.layout.fragment_list)
public class MessageFragment extends BaseListFragment<UserMsg> {

    MainActivity_ activity_;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity_ = (MainActivity_) activity;
    }

    @Override
    public int getFragmentTitle() {
        return R.string.message_center;
    }

    @Override
    public void afterViews() {
        super.afterViews();
    }

    @Override
    public BaseApi getApi() {
        return new MessageApi(getActivity());
    }

    @Override
    public String getJSONObjectListKey() {
        return "msgList";
    }

    @Override
    public int getEmptyView() {
        return R.layout.message_empty_view;
    }

    @Override
    public BaseAdapter getAdapter() {
        return new MessageAdapter(getActivity());
    }

    @ItemClick
    public void listview(int position) {
        UserMsg msg = adapter.getItem(position);
        if (msg != null) {
            if (activity_ != null) {
                activity_.startMapFragment(msg);
            }
        }
    }

    @Override
    public Class<UserMsg> getClazz() {
        return UserMsg.class;
    }
}
