package com.ttxr.application;

import android.app.Application;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.L;
import com.ttxr.share.CommonShared;
import com.ttxr.util.Url;
import com.umeng.fb.push.FeedbackPush;

import org.androidannotations.annotations.EApplication;

@EApplication
public class AppClass extends Application {

    public AsyncHttpClient httpClient;
    public CommonShared cs;

    public static final DisplayImageOptions options_no_default = new DisplayImageOptions.Builder()
            .cacheInMemory(true).cacheOnDisk(true).build();

    public void onCreate() {

        //友盟反馈实时推送
        FeedbackPush.getInstance(this).init(false);

        httpClient = new AsyncHttpClient();
        httpClient.setCookieStore(new PersistentCookieStore(this));
        httpClient.setURLEncodingEnabled(false);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(
                options_no_default).build();
        L.writeLogs(false);
        L.writeDebugLogs(false);
        ImageLoader.getInstance().init(config);

        cs = new CommonShared(this);

        Url.HOST = cs.getUrl();
    }

    ;

}
