package com.ttxr.share;

import java.io.Serializable;
import java.util.Date;

public class SharedData implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String key;

    private String mStr;

    /**
     */
    private boolean mBoolean;

    /**
     */
    private int mInt;

    /**
     */
    private Date mDate;

    /**
     */
    private long mLong;

    /**
     */
    private float mFloat;

    private DataType dataType;

    public static final String ID = "id";

    public static final String KEY = "key";

    public static final String M_STR = "m_str";

    public static final String M_BOOLEAN = "m_boolean";

    public static final String M_INT = "m_int";

    public static final String M_DATE = "m_date";

    public static final String M_LONG = "m_long";

    public static final String M_FLOAT = "m_float";

    public static final String DATA_TYPE = "data_type";

    public static final String TABLE_NAME = "t_global_data";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getmStr() {
        return mStr;
    }

    public void setmStr(String mStr) {
        this.mStr = mStr;
    }

    public boolean ismBoolean() {
        return mBoolean;
    }

    public void setmBoolean(boolean mBoolean) {
        this.mBoolean = mBoolean;
    }

    public int getmInt() {
        return mInt;
    }

    public void setmInt(int mInt) {
        this.mInt = mInt;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public long getmLong() {
        return mLong;
    }

    public void setmLong(long mLong) {
        this.mLong = mLong;
    }

    public float getmFloat() {
        return mFloat;
    }

    public void setmFloat(float mFloat) {
        this.mFloat = mFloat;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }


    @Override
    public String toString() {
        return "SharedData{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", mStr='" + mStr + '\'' +
                ", mBoolean=" + mBoolean +
                ", mInt=" + mInt +
                ", mDate=" + mDate +
                ", mLong=" + mLong +
                ", mFloat=" + mFloat +
                ", dataType=" + dataType +
                '}';
    }
}