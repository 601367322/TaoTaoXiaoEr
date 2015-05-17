package com.ttxr.activity.user;

import android.widget.Button;
import android.widget.EditText;

import com.ttxr.activity.LoginAndRegActivity_;
import com.ttxr.activity.MainActivity_;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.application.AM;
import com.ttxr.bean.request_model.RegisterRequestDTO;
import com.ttxr.util.MD5andKL;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;
import com.umeng.message.UmengRegistrar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * Created by mr.shen on 2015/5/16.
 */
@EActivity(R.layout.activity_reg_set_password)
public class SetPassWordActivity extends BaseBackActivity {

    @ViewById
    EditText password;
    @ViewById
    Button done;
    @Extra
    String phone;

    @Click
    public void done() {

        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setUserAccount(phone);
        request.setUserPassword(MD5andKL.MD5(password.getText().toString()));

        String device_token = UmengRegistrar.getRegistrationId(getApplicationContext());
        if (!Util.isEmpty(device_token)) {
            request.setUserMsgId(device_token);
        }
        request.setRegSource("1");
        request.setVersion(Util.getAppVersionName(this));

        if (!ac.cs.getLat().equals("")) {
            request.setUserLatitude(ac.cs.getLat());
            request.setUserLongitude(ac.cs.getLng());
        }

        ac.httpClient.post(this, Url.REG, Util.getDefaultRequestParams(request), new MyJsonHttpResponseHandler(context, getString(R.string.reging)) {

            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                Util.toast(context, jo.optString(Url.RET_MESSAGE));
                AM.getActivityManager().popActivity(RegActivity_.class);
                AM.getActivityManager().popActivity(LoginActivity_.class);
                AM.getActivityManager().popActivity(LoginAndRegActivity_.class);
                finish();
                if (!AM.getActivityManager().contains(MainActivity_.class)) {
                    MainActivity_.intent(context).start();
                }
            }

        });

    }

    @TextChange
    public void password() {
        if (Util.isPassword(password.getText().toString())) {
            done.setEnabled(true);
        } else {
            done.setEnabled(false);
        }
    }
}
