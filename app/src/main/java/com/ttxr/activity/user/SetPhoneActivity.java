package com.ttxr.activity.user;

import android.widget.EditText;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.bean.UserBeanTable;
import com.ttxr.bean.request_model.UpdateUserRequestDTO;
import com.ttxr.db.DBHelper;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * Created by Shen on 2015/5/25.
 */
@OptionsMenu(R.menu.new_address_menu)
@EActivity(R.layout.activity_set_phone)
public class SetPhoneActivity extends BaseBackActivity{

    @ViewById
    public EditText phone;
    @Extra
    public UserBeanTable bean;

    @Override
    public void afterViews() {
        super.afterViews();
        phone.setText(bean.bean.userPhone);
    }

    @OptionsItem
    public void save(){
        final String nickname_str = phone.getText().toString();
        if(!Util.isMobileNO(nickname_str)){
            Util.toast(this, getString(R.string.please_set_right_phone));
            return;
        }

        UpdateUserRequestDTO request = new UpdateUserRequestDTO();
        request.setUserPhone(nickname_str);
        ac.httpClient.post(Url.UPDATE_USERINFO, Util.getTokenRequestParams(this,request), new MyJsonHttpResponseHandler(this,getString(R.string.saving)) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                bean.bean.userPhone = nickname_str;
                DBHelper.getUserDao(getApplicationContext()).createOrUpdate(bean);
                finish();
            }
        });
    }
}
