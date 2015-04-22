/*
package com.ttxr.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.ttxr.Model.MenuBean;
import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.adapter.MenuAdapter;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by mr.shen on 2015/4/19.
 *//*

@EFragment(R.layout.fragment_left_menu)
public class MenuFragment extends BaseFragment {

    @ViewById(R.id.listview)
    ListView listView;
    MenuAdapter adapter;
    MainActivity_ activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity_) activity;
    }

    @Override
    public void afterViews() {

        List<MenuBean> list = new ArrayList<>();
        list.add(new MenuBean(R.drawable.ic_state_dinner, "我的订单", false));
        list.add(new MenuBean(R.drawable.ic_state_dinner, "接单记录", false));
        list.add(new MenuBean(R.drawable.ic_state_dinner, "消息中心", false));
        list.add(new MenuBean(R.drawable.ic_state_dinner, "我的账户", false));
        list.add(new MenuBean(R.drawable.ic_state_dinner, "设置", false));
        adapter = new MenuAdapter(list, getActivity());
        listView.setAdapter(adapter);
    }

    @Click(R.id.head)
    public void headClick(View view) {
        if (activity != null) {
            activity.replaceFragment(R.id.content, new UserInfoFragment_());
            activity.main.close();
        }
    }

    @ItemClick(R.id.listview)
    public void onListItemClick(int position) {
        if(activity!=null) {
            MenuBean bean = adapter.getItem(position);
            switch (position) {
                case 0:
                    activity.replaceFragment(R.id.content,new MainFragment_());
                    break;
            }
            activity.main.close();
        }
    }
}
*/
