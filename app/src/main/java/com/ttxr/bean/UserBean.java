package com.ttxr.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mr.shen on 2015/5/16.
 */
@DatabaseTable(tableName = "user_bean")
public class UserBean implements Serializable {

    @DatabaseField (id = true)
    private int id;
    @DatabaseField
    private String latitude;
    @DatabaseField
    private String longitude;
    @DatabaseField
    private String nickName;
    @DatabaseField
    private String photoUrl;
    @DatabaseField
    private String userAccount;
    @DatabaseField
    private String userAddress;
    @DatabaseField
    private String userCode;
    @DatabaseField
    private String userLevel;
    @DatabaseField
    private String userPhone;
    @DatabaseField
    private String userSex;

    public UserBean() {
        super();
    }

    public UserBean(int id, String latitude, String longitude, String nickName, String photoUrl, String userAccount, String userAddress, String userCode, String userLevel, String userPhone, String userSex) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nickName = nickName;
        this.photoUrl = photoUrl;
        this.userAccount = userAccount;
        this.userAddress = userAddress;
        this.userCode = userCode;
        this.userLevel = userLevel;
        this.userPhone = userPhone;
        this.userSex = userSex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
