package com.ttxr.activity.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttxr.activity.R;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Util;
import com.ttxr.weight.listview.XListView;
import com.ttxr.weight.swipe.SwipeRefreshLayout;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.List;

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

    public int currentPage = 0;
    public int totalPage = 1;
    public int pageSize = 10;

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

    @Override
    public void onRefresh() {
        currentPage = 0;
        onPullToRefresh();
    }

    @Override
    public void onLoadMore() {
        onPullToRefresh();
    }

    private void onPullToRefresh() {
        final BaseApi api = getApi();
        if(pageEnable()) {
            api.setPageRequest(currentPage, pageSize);
        }
        ac.httpClient.post(api.getUrl(), api.getParams(), new MyJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                totalPage = Util.getTotalPages(jo);
                onSuccessCallBack(jo);

                List list = Util.jsonToList(jo.optString(getJSONObjectListKey()), getClazz());
                onSuccessRefreshUI(list);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                onFinshRefreshUI();
            }
        });
    }

    @UiThread
    public void refresh() {
        refreshLayout.setRefreshing(true);
    }

    public int getEmptyView() {
        return -1;
    }

    public abstract BaseAdapter<T> getAdapter();

    private void onSuccessRefreshUI(List list) {
        if (list != null) {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            currentPage++;
        }
        if (currentPage >= totalPage) {
            listview.setPage(-1);
        } else {
            listview.setPage(currentPage);
        }
    }

    private void onFinshRefreshUI() {
        refreshLayout.setRefreshing(false);
        listview.stop();
    }

    @ItemClick
    public void listview(int position) {

    }

    public abstract BaseApi getApi();

    public void onSuccessCallBack(JSONObject jo) {
    }

    public abstract String getJSONObjectListKey();

    public abstract Class<?> getClazz();

    public boolean pageEnable(){
        return true;
    }
}
