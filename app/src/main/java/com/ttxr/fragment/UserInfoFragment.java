package com.ttxr.fragment;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.activity.user.NewAddressActivity_;
import com.ttxr.activity.user.SetNickNameActivity_;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.ImageUtil;
import com.ttxr.util.StaticUtil;
import com.ttxr.util.Util;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;

import java.io.File;
import java.util.Date;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EFragment(R.layout.fragment_userinfo)
public class UserInfoFragment extends BaseFragment implements IFragmentTitle {

    public static final int Album = 2, Camera = 1;

    String filename;

    @Override
    public int getFragmentTitle() {
        return R.string.userinfo;
    }

    @Click
    public void address_btn() {
        NewAddressActivity_.intent(this).start();
    }

    @Click
    public void nickname_btn() {
        SetNickNameActivity_.intent(this).start();
    }

    @Click
    public void sex_btn() {
        new AlertDialog.Builder(getActivity()).setItems(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    @Click
    public void logo_btn() {
        new AlertDialog.Builder(getActivity()).setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
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
                            Util.toast(getActivity(), "亲，请检查是否安装存储卡!");
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
                            Util.toast(getActivity(), "亲，请检查是否安装存储卡!");
                        }
                        break;
                }
            }
        }).create().show();
    }

    @OnActivityResult(value = Camera)
    @Background
    void onCameraResult() {
        if (filename != null) {
            File fi = new File(filename);
            if (fi != null && fi.exists()) {
                ImageUtil.downsize(filename, filename, getActivity());
                //todo
            }
            fi = null;
        }
    }

    @OnActivityResult(value = Album)
    @Background
    void onAlbumResult(Intent intent) {
        if (intent == null || getActivity() == null) {
            return;
        }
        ContentResolver resolver = getActivity().getContentResolver();
        Uri imgUri = intent.getData();
        try {
            Cursor cursor = resolver.query(imgUri, null, null, null, null);
            cursor.moveToFirst();
            filename = cursor.getString(1);
            ImageUtil.downsize(
                    filename,
                    filename = StaticUtil.APKCardPathImg
                            + new Date().getTime(), getActivity());
            //todo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
