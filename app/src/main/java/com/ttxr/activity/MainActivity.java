package com.ttxr.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import com.ttxr.activity.base.BaseActivity;
import com.ttxr.fragment.MapFragment_;
import com.ttxr.fragment.NavigationDrawerFragment;
import com.ttxr.interfaces.IFragmentTitle;

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
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new MapFragment_())
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(Fragment fragment) {
        replaceFragment(R.id.content, fragment);
        if(fragment instanceof IFragmentTitle) {
            getSupportActionBar().setTitle(((IFragmentTitle) fragment).getFragmentTitle());
        }
        mNavigationDrawerFragment.closeDrawer();
    }
}
