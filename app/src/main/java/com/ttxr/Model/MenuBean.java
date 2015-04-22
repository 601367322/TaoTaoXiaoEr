package com.ttxr.Model;

import java.io.Serializable;

/**
 * Created by mr.shen on 2015/4/19.
 */
public class MenuBean implements Serializable {

    public int icon;
    public String title;
    public boolean badge;

    public MenuBean(int icon, String title, boolean badge) {
        this.icon = icon;
        this.title = title;
        this.badge = badge;
    }
}
