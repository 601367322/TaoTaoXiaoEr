package com.ttxr.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import com.ttxr.activity.base.BaseActivity;
import com.ttxr.bean.UserMsg;
import com.ttxr.fragment.MapFragment;
import com.ttxr.fragment.MapFragment_;
import com.ttxr.fragment.NavigationDrawerFragment;
import com.ttxr.interfaces.IFragmentTitle;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    @FragmentById(R.id.navigation_drawer)
    public NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public void afterViews() {

        //umeng 消息推送
        PushAgent mPushAgent = PushAgent.getInstance(getApplicationContext());
        mPushAgent.enable();

        //umeng 更新
        UmengUpdateAgent.update(this);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));//初始化侧滑菜单

        onNavigationDrawerItemSelected(new MapFragment_());//加载地图界面
    }

    /**
     * 侧滑菜单点击事件，切换界面
     * @param fragment
     */
    @Override
    public void onNavigationDrawerItemSelected(Fragment fragment) {
        replaceFragment(R.id.content, fragment);
        if(fragment instanceof IFragmentTitle) {
            getSupportActionBar().setTitle(((IFragmentTitle) fragment).getFragmentTitle());
        }
        mNavigationDrawerFragment.closeDrawer();
    }

    public void startMapFragment(UserMsg bean){
        MapFragment fragment = MapFragment_.builder().msg(bean).build();
        onNavigationDrawerItemSelected(fragment);
    }

    /*@Override
    public boolean destoryPop() {
        return false;
    }*/
}
