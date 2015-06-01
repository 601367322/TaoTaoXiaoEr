package com.ttxr.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Shen on 2015/5/21.
 */
@DatabaseTable(tableName = "user_bean_table")
public class UserBeanTable implements Serializable{

    @DatabaseField(id = true)
    public int id;
    @DatabaseField
    public String userCode;
    @DatabaseField
    public String userAccount;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public UserBean bean;

    public UserBeanTable() {
        super();
    }

    public UserBeanTable(UserBean bean) {
        this.bean = bean;
        this.userCode = bean.userCode;
        this.userAccount = bean.userAccount;
    }
}
