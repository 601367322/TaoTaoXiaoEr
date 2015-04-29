package com.ttxr.activity;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbb on 2015/4/29.
 */
@EActivity(R.layout.activity_viewpager)
public class ViewPagerActivity extends Activity {

    @ViewById
    ViewPager viewpager;

    List<Integer> imgs = new ArrayList<>();

    @AfterViews
    public void afterViews() {
        imgs.add(R.drawable.launcher_1);
        imgs.add(R.drawable.launcher_2);
        viewpager.setAdapter(pagerAdapter);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(imgs.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return imageView;
        }

    };
}
