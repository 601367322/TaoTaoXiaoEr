package com.ttxr.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.ttxr.activity.LoginAndRegActivity_;
import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.application.AM;
import com.ttxr.bean.UserBean;
import com.ttxr.bean.UserBeanTable;
import com.ttxr.bean.request_model.LoginRequestDTO;
import com.ttxr.db.DBHelper;
import com.ttxr.location.GDLocation;
import com.ttxr.util.MD5andKL;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;
import com.umeng.message.UmengRegistrar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * Created by mr.shen on 2015/5/17.
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseBackActivity {


    @ViewById
    EditText phone;
    @ViewById
    EditText password;
    @ViewById
    TextView toReg;
    @ViewById
    TextView findPassword;
    @ViewById
    Button loginBtn;
    RuntimeExceptionDao<UserBeanTable, Integer> userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GDLocation(this, null, true);
        userDao = DBHelper.getDao_(this, UserBeanTable.class);
    }

    @Click
    public void login_btn() {
        String str_phone = phone.getText().toString();
        String str_password = password.getText().toString();

        LoginRequestDTO request = new LoginRequestDTO();
        request.setUserAccount(str_phone);
        request.setUserPassword(MD5andKL.MD5(str_password));
        request.setUserMsgId(UmengRegistrar.getRegistrationId(getApplicationContext()));
        request.setLatitude(ac.cs.getLat());
        request.setLongitude(ac.cs.getLng());

        ac.httpClient.post(this, Url.LOGIN, Util.getDefaultRequestParams(request), new MyJsonHttpResponseHandler(this, getString(R.string.logining)) {

            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                UserBean userBean = new Gson().fromJson(jo.getString("user"), new TypeToken<UserBean>() {
                }.getType());
                if (userBean != null) {
                    userDao.createOrUpdate(new UserBeanTable(userBean));
                    AM.getActivityManager().popActivity(LoginAndRegActivity_.class);
                    finish();
                    MainActivity_.intent(context).start();
                }
            }
        });
    }

    @Click
    public void to_reg() {
        RegActivity_.intent(this).start();
    }

    @Click
    public void find_password() {

    }

    @TextChange
    public void phone() {
        checkLoginBtn();
    }

    @TextChange
    public void password() {
        checkLoginBtn();
    }

    public void checkLoginBtn() {
        String str_phone = phone.getText().toString();
        String str_password = password.getText().toString();
        if (!TextUtils.isEmpty(str_phone) && !TextUtils.isEmpty(str_password)) {
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setEnabled(false);
        }
    }
}
