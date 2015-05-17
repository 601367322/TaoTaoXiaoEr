package com.ttxr.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.ttxr.activity.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mr.shen on 2015/5/16.
 */
public class Util {

    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null) {
            return false;
        }
        Pattern p = Pattern.compile("^[1]+\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isCode(String code) {
        if (code == null) {
            return false;
        }
        Pattern p = Pattern.compile("^\\d{6}$");
        Matcher m = p.matcher(code);
        return m.matches();
    }

    public static long locationTime;

    public static boolean isFastLocation() {
        long time = System.currentTimeMillis();
        long timeD = time - locationTime;
        if (0 < timeD && timeD < 15000) {
            return true;
        }
        return false;
    }

    public static final boolean isPassword(String password) {
        if (password.length() > 20 || password.length() < 6) {
            return false;
        } else if (!password.matches("^[a-zA-Z0-9 -]+$")) {
            return false;
        }
        return true;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    public static void toast(Context context, String str) {
        if (context != null && str != null)
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context) {
        if (context != null)
            Toast.makeText(context, context.getString(R.string.unknow), Toast.LENGTH_SHORT).show();
    }

    public static ProgressDialog progress(Context context, String str) {
        if (context != null && str != null)
            return ProgressDialog.show(context, null, str, false, true);
        else
            return null;
    }

    public static RequestParams getDefaultRequestParams(Object params) {
        return new RequestParams(Url.REQUEST_DATA, new Gson().toJson(params).toString());
    }

    // 判断手机有无存储卡
    public static boolean existSDcard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else
            return false;
    }
}
