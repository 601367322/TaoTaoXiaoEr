package com.ttxr.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.activity.user.SetAddressActivity_;
import com.ttxr.activity.user.SetNickNameActivity_;
import com.ttxr.activity.user.SetPhoneActivity_;
import com.ttxr.activity.user.SetPhotoActivity_;
import com.ttxr.bean.UserBeanTable;
import com.ttxr.bean.request_model.UpdateUserRequestDTO;
import com.ttxr.db.DBHelper;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.ImageUtil;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * Created by mr.shen on 2015/4/19.
 */
@EFragment(R.layout.fragment_userinfo)
public class UserInfoFragment extends BaseFragment implements IFragmentTitle {

    public static final int Album = 2, Camera = 1;
    @ViewById
    ImageView logo;
    @ViewById
    TextView nickname;
    @ViewById
    TextView address;
    @ViewById
    TextView sex;
    @ViewById
    TextView phone;

    RuntimeExceptionDao<UserBeanTable, Integer> userDao;

    String filename;
    UserBeanTable userBean;

    @Override
    public void afterViews() {
        super.afterViews();
        userDao = DBHelper.getDao_(getActivity(), UserBeanTable.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            userBean = userDao.queryForFirst(userDao.queryBuilder().prepare());
            ImageLoader.getInstance().displayImage(userBean.bean.photoUrl, logo, ImageUtil.options_default);
            if (!TextUtils.isEmpty(userBean.bean.nickName)){
                nickname.setText(userBean.bean.nickName);
            }else{
                nickname.setText(getString(R.string.empty));
            }
            if (!TextUtils.isEmpty(userBean.bean.userSex)) {
                if (userBean.bean.userSex.equals("1"))
                    sex.setText(getString(R.string.man));
                else
                    sex.setText(getString(R.string.woman));
            }
            if (!TextUtils.isEmpty(userBean.bean.userPhone)){
                phone.setText(userBean.bean.userPhone);
            }else{
                phone.setText(getString(R.string.empty));
            }
            if (!TextUtils.isEmpty(userBean.bean.userAddress)) {
                address.setText(userBean.bean.userAddress);
            }else{
                address.setText(getString(R.string.empty));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getFragmentTitle() {
        return R.string.userinfo;
    }

    @Click
    public void address_btn() {
        SetAddressActivity_.intent(this).bean(userBean).start();
    }

    @Click
    public void nickname_btn() {
        SetNickNameActivity_.intent(this).bean(userBean).start();
    }

    @Click
    public void sex_btn() {
        new AlertDialog.Builder(getActivity()).setItems(new String[]{getString(R.string.woman), getString(R.string.man)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                UpdateUserRequestDTO request = new UpdateUserRequestDTO();
                request.setUserSex(String.valueOf(which));
                ac.httpClient.post(Url.getInstance().getURL(Url.UPDATE_USERINFO), Util.getTokenRequestParams(getActivity(), request), new MyJsonHttpResponseHandler(getActivity(), getString(R.string.saving)) {
                    @Override
                    public void onSuccessRetCode(JSONObject jo) throws Throwable {
                        userBean.bean.userSex = String.valueOf(which);
                        userDao.createOrUpdate(userBean);
                        onResume();
                    }
                });
            }
        }).create().show();
    }

    @Click
    public void phone_btn(){
        SetPhoneActivity_.intent(this).bean(userBean).start();
    }

    @Click
    public void logo_btn() {
        SetPhotoActivity_.intent(getActivity()).bean(userBean).start();
    }

}
