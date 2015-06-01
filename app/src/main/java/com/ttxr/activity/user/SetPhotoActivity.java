package com.ttxr.activity.user;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.loopj.android.http.RequestParams;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseActivity;
import com.ttxr.bean.UserBeanTable;
import com.ttxr.db.DBHelper;
import com.ttxr.util.ImageUtil;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.StaticUtil;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;

/**
 * Created by Shen on 2015/5/23.
 */
@EActivity
public class SetPhotoActivity extends BaseActivity {

    String filename;
    public static final int Album = 2, Camera = 1;
    @Extra
    UserBeanTable bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog dialog = new AlertDialog.Builder(this).setItems(new String[]{getString(R.string.camera), getString(R.string.album)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 1:
                        if (Util.existSDcard()) {
                            Intent intent = new Intent(); // 调用照相机
                            String messagepath = StaticUtil.APKCardPathImg;
                            File fa = new File(messagepath);
                            if (!fa.exists()) {
                                fa.mkdirs();
                            }
                            filename = messagepath
                                    + new Date().getTime();// 图片路径
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(filename)));
                            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, Camera);
                        } else {
                            Util.toast(SetPhotoActivity.this, "亲，请检查是否安装存储卡!");
                        }
                        break;
                    case 0:
                        if (Util.existSDcard()) {
                            Intent intent = new Intent();
                            String messagepath = StaticUtil.APKCardPathImg;
                            File fa = new File(messagepath);
                            if (!fa.exists()) {
                                fa.mkdirs();
                            }
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, Album);
                        } else {
                            Util.toast(SetPhotoActivity.this, "亲，请检查是否安装存储卡!");
                        }
                        break;
                }
            }
        }).create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        dialog.show();
    }

    @OnActivityResult(value = Camera)
    @Background
    void onCameraResult() {
        if (filename != null) {
            File fi = new File(filename);
            if (fi != null && fi.exists()) {
                ImageUtil.downsize(filename, filename, this);
                sendFile(filename);
            }
            fi = null;
        }
    }

    @OnActivityResult(value = Album)
    @Background
    void onAlbumResult(Intent intent) {
        if (intent == null || this == null) {
            return;
        }
        ContentResolver resolver = this.getContentResolver();
        Uri imgUri = intent.getData();
        try {
            Cursor cursor = resolver.query(imgUri, null, null, null, null);
            cursor.moveToFirst();
            filename = cursor.getString(1);
            ImageUtil.downsize(
                    filename,
                    filename = StaticUtil.APKCardPathImg
                            + new Date().getTime(), this);
            sendFile(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @UiThread
    public void sendFile(String filename) {
        RequestParams params = new RequestParams();
        try {
            params.put("image", new File(filename));
            params.put("token", bean.bean.token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyJsonHttpResponseHandler hanlder = new MyJsonHttpResponseHandler(this, getString(R.string.saving), new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        }) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                if(jo.has("photoUrl")){
                    bean.bean.photoUrl = jo.optString("photoUrl");
                    DBHelper.getUserDao(getApplicationContext()).createOrUpdate(bean);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                finish();
            }
        };
        ac.httpClient.post(Url.UPDATE_LOGO, params, hanlder);
    }
}
