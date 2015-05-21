package com.ttxr.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.ttxr.util.Util;

import java.io.Serializable;

/**
 * Created by Shen on 2015/5/21.
 */
@DatabaseTable
public abstract class BaseBean<T> implements Serializable {

    protected T bean;

    public T getBean() {
        if(bean == null){
            if(!Util.isEmpty(content)){
                bean = new Gson().fromJson(content, new TypeToken<T>() {
                }.getType());
            }
        }
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
        this.content = new Gson().toJson(bean);
    }

    @DatabaseField
    protected String content;

    public String getContent() {
        if(Util.isEmpty(content)){
            if(bean != null) {
                content = new Gson().toJson(bean);
            }else {
                content = new Gson().toJson(this);
            }
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        bean = new Gson().fromJson(content, new TypeToken<T>() {
        }.getType());
    }
}
