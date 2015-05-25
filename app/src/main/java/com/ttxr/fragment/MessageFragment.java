package com.ttxr.fragment;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.MessageAdapter;
import com.ttxr.bean.UserMsg;
import com.ttxr.bean.request_model.PageResquest;
import com.ttxr.bean.request_model.UserMsgRequestDTO;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    public void onRefresh() {
        UserMsgRequestDTO request1 = new UserMsgRequestDTO();
        request1.setMsgType(null);
        request1.setStatus(null);
        PageResquest pageRequest = new PageResquest();
        pageRequest.setCurPage(currentPage);
        pageRequest.setPageSize(pageSize);
        request1.setPage(pageRequest);

        ac.httpClient.post(Url.MESSAGE_LIST, Util.getTokenRequestParams(getActivity(), request1), new MyJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                pageSize = Util.getPageSize(jo);
                if (jo.has("msgList")) {
                    List<UserMsg> list = new Gson().fromJson(jo.optString("msgList"), new TypeToken<ArrayList<UserMsg>>() {
                    }.getType());
                    onSuccessRefreshUI(list);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                onFinshRefreshUI();
            }
        });
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
            if(activity_!=null){
                activity_.startMapFragment(msg);
            }
        }
    }
}
