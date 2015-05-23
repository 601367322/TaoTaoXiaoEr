package com.ttxr.fragment;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseAdapter;
import com.ttxr.activity.base.BaseListFragment;
import com.ttxr.adapter.OrderHistoryAdapter;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Shen on 2015/5/23.
 */
@EFragment(R.layout.fragment_list)
public class OrderHistoryListFragment extends BaseListFragment {

    @Override
    public BaseAdapter getAdapter() {
        return new OrderHistoryAdapter(getActivity());
    }

    @Override
    public int getFragmentTitle() {
        return 0;
    }

    @Override
    public void onRefresh() {
       /* ac.httpClient.post(Url.GET_ORDER_REQUEST, Util.getTokenRequestParams(getActivity(),null), new MyJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                onSuccessRefreshUI(null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

        ac.httpClient.post(Url.GET_MY_ORDER, Util.getTokenRequestParams(getActivity(),null), new MyJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                onSuccessRefreshUI(null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
*/
    }
}
