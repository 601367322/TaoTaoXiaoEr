package com.ttxr.activity.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.location.GDLocation;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Util;

import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * Created by mr.shen on 2015/5/16.
 */
@EActivity(R.layout.activity_reg)
public class RegActivity extends BaseBackActivity {

    @ViewById
    EditText phone;
    @ViewById
    EditText code;
    @ViewById
    Button code_btn;
    @ViewById
    CheckBox agree;
    @ViewById
    Button reg_next;

    @ViewById
    TextView remind_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GDLocation(this, null, true);
    }

    @Click
    public void reg_next() {
        final String str_phone = phone.getText().toString();
        String str_code = code.getText().toString();
        boolean b_agree = agree.isChecked();

        SetPassWordActivity_.intent(this).phone(str_phone).start();
    }

    @Click
    public void code_btn() {
        String str_phone = phone.getText().toString();
        if (Util.isMobileNO(str_phone)) {

            new AlertDialog.Builder(this).setTitle(getString(R.string.reg_phone_alert_title)).setMessage(str_phone).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    phone.setVisibility(View.GONE);
                    remind_text.setVisibility(View.VISIBLE);
                    code.setEnabled(true);
                }
            }).create().show();

            ac.httpClient.post(this, "", null, new MyJsonHttpResponseHandler(this) {

                @Override
                public void onSuccessRetCode(JSONObject jo) {

                }

                @Override
                public void onFailRetCode(JSONObject jo) {

                }
            });
        }
    }

    @TextChange
    public void phone() {
        checkEnableNextBtn();
    }

    @TextChange
    public void code() {
        checkEnableNextBtn();
    }

    @CheckedChange
    public void agree() {
        checkEnableNextBtn();
    }

    public void checkEnableNextBtn() {
        String str_phone = phone.getText().toString();
        String str_code = code.getText().toString();
        boolean b_agree = agree.isChecked();

        if (Util.isMobileNO(str_phone) && Util.isCode(str_code) && b_agree) {
            reg_next.setEnabled(true);
        } else {
            reg_next.setEnabled(false);
        }
    }

}
