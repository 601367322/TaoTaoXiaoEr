package com.ttxr.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.ttxr.activity.LoginAndRegActivity_;
import com.ttxr.activity.R;
import com.ttxr.application.AM;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mr.shen on 2015/5/16.
 */
public abstract class MyJsonHttpResponseHandler extends JsonHttpResponseHandler {

    String progress = null;
    Context context = null;
    ProgressDialog dialog = null;
    DialogInterface.OnCancelListener listener;

    public MyJsonHttpResponseHandler(Context context) {
        this(context, null);
    }

    public MyJsonHttpResponseHandler(Context context, String progress) {
        this.context = context;
        this.progress = progress;
        setCharset("GBK");
    }

    public MyJsonHttpResponseHandler(Context context, String progress, DialogInterface.OnCancelListener listener) {
        this.context = context;
        this.progress = progress;
        this.listener = listener;
        setCharset("GBK");
    }

    @Override
    public void onStart() {
        if (progress != null && context != null) {
            dialog = Util.progress(context, progress);
            if(listener!=null){
                dialog.setOnCancelListener(listener);
            }
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        LogUtil.d(response.toString());
        onSuccess(response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        onFailure();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        switch (statusCode) {
            case 401:
                if (context != null) {
                    Util.toast(context, context.getString(R.string.unlogin));
                    AM.getActivityManager().popAllActivity();
                    LoginAndRegActivity_.intent(context).start();
                }
                break;
            default:
                onFailure();
                break;
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        onFailure();
    }

    public void onSuccess(JSONObject jo) {
        try {
            if (jo.has(Url.RET_CODE)) {
                int retCode = jo.optInt(Url.RET_CODE);
                switch (retCode) {
                    case 0:
                        onSuccessRetCode(jo);
                        break;
                    case 1:
                        onFailRetCode(jo);
                        break;
                    case 2:
                        onFailRetCode(jo);
                        break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            onFailure();
        }
    }

    public abstract void onSuccessRetCode(JSONObject jo) throws Throwable;

    public void onFailRetCode(JSONObject jo) {
        if (jo.has(Url.RET_MESSAGE) && context != null) {
            Util.toast(context, jo.optString(Url.RET_MESSAGE));
        }
    }

    public void onFailure() {
        Util.toastError(context);
    }

    @Override
    public void onFinish() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
