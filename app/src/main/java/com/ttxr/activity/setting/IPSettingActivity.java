package com.ttxr.activity.setting;

import android.text.TextUtils;
import android.widget.EditText;

import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseBackActivity;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Shen on 2015/5/30.
 */
@OptionsMenu(R.menu.new_address_menu)
@EActivity(R.layout.activity_ip_setting)
public class IPSettingActivity extends BaseBackActivity {


    @ViewById
    EditText ip;
    @ViewById
    EditText port;
    @ViewById
    EditText appName;

    @Override
    public void afterViews() {
        super.afterViews();
        String url = ac.cs.getUrl();
        url = url.replace("http://", "");
        String ip_str = "";
        String port_str = "";
        String app_name_str = "";
        if (url.indexOf(":") > 0) {
            ip_str = url.substring(0, url.indexOf(":"));
            port_str = url.substring(url.indexOf(":") + 1, url.indexOf("/"));
            app_name_str = url.substring(url.indexOf("/") + 1).replace("/", "");
        } else {
            ip_str = url.substring(0, url.indexOf("/"));
            app_name_str = url.substring(url.indexOf("/") + 1).replace("/", "");
        }
        ip.setText(ip_str);
        port.setText(port_str);
        appName.setText(app_name_str);
    }

    @OptionsItem
    public void save() {
        if (TextUtils.isEmpty(ip.getText().toString())) {
            Util.toast(this, "IP/域名不能为空");
            return;
        }
        StringBuilder url = new StringBuilder("http://" + ip.getText().toString());
        if (!TextUtils.isEmpty(port.getText().toString())) {
            url.append(":" + port.getText().toString());
        }
        url.append("/");
        if (!TextUtils.isEmpty(appName.getText().toString())) {
            url.append(appName.getText().toString() + "/");
        }
        ac.cs.setUrl(url.toString());
        Url.getInstance(url.toString());

        finish();
    }
}
