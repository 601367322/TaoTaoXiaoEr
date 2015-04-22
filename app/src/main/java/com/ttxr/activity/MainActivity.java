package com.ttxr.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.ttxr.activity.base.BaseActivity;
import com.ttxr.fragment.MainFragment_;
import com.ttxr.fragment.NavigationDrawerFragment;

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
                .replace(R.id.content, new MainFragment_())
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}
