package com.ttxr.activity.user;

import android.text.TextUtils;
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
 * Created by mr.shen on 2015/5/17.
 */
@OptionsMenu(R.menu.new_address_menu)
@EActivity(R.layout.activity_set_nickname)
public class SetNickNameActivity extends BaseBackActivity {

    @ViewById
    public EditText nickname;
    @Extra
    public UserBeanTable bean;

    @Override
    public void afterViews() {
        super.afterViews();
        nickname.setText(bean.bean.nickName);
    }

    @OptionsItem
    public void save(){
        final String nickname_str = nickname.getText().toString();
        if(TextUtils.isEmpty(nickname_str)){
            Util.toast(this,getString(R.string.please_set_nickname));
            return;
        }else if(nickname_str.length()>20){
            Util.toast(this,getString(R.string.too_long_nickname));
            return;
        }

        UpdateUserRequestDTO request = new UpdateUserRequestDTO();
        request.setNickName(nickname_str);
        ac.httpClient.post(Url.getInstance().getURL(Url.UPDATE_USERINFO), Util.getTokenRequestParams(this,request), new MyJsonHttpResponseHandler(this,getString(R.string.saving)) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                bean.bean.nickName = nickname_str;
                DBHelper.getUserDao(getApplicationContext()).createOrUpdate(bean);
                finish();
            }
        });
    }
}
