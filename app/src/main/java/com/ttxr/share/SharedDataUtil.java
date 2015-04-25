package com.ttxr.share;


import android.content.Context;
import android.text.TextUtils;

import java.util.Date;
import java.util.HashMap;

public class SharedDataUtil {
    private SharedDataSqLiteHelper sharedDataSqLiteHelper;

    private static SharedDataUtil sharedDataUtil;

    private SharedDataEditor sharedDataEditor;

    private SharedDataUtil(Context context) {
        if (sharedDataSqLiteHelper == null || sharedDataSqLiteHelper.isClosed()) {
            this.sharedDataSqLiteHelper = new SharedDataSqLiteHelper(context);
        }
    }

    public static synchronized SharedDataUtil getInstance(Context context) {
        if (context == null) {
            return null;
        }

        if (sharedDataUtil == null) {
            sharedDataUtil = new SharedDataUtil(context);
        }

        return sharedDataUtil;
    }

    public String getString(String key, String defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.STRING) {
            return defaultValue;
        }

        return sharedData.getmStr();
    }
    public boolean getBoolean(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.BOOLEAN) {
            return defaultValue;
        }

        return sharedData.ismBoolean();
    }

    public int getInt(String key, int defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.INT) {
            return defaultValue;
        }

        return sharedData.getmInt();
    }
    public Date getDate(String key, Date defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.DATA) {
            return defaultValue;
        }

        return sharedData.getmDate();
    }

    public long getLong(String key, long defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.LONG) {
            return defaultValue;
        }

        return sharedData.getmLong();
    }

    public float getFloat(String key, float defaultValue) {
        if (TextUtils.isEmpty(key))
            return defaultValue;

        SharedData sharedData = this.sharedDataSqLiteHelper.getGlobalDataByKey(key);

        if (sharedData == null || sharedData.getDataType() != DataType.FLOAT) {
            return defaultValue;
        }

        return sharedData.getmFloat();
    }


    public boolean contains(String key) {
        if (TextUtils.isEmpty(key))
            return false;

        return this.sharedDataSqLiteHelper.contains(key);
    }

    public boolean remove(String key) {
        if (TextUtils.isEmpty(key))
            return false;

        return this.sharedDataSqLiteHelper.remove(key);
    }

    public boolean clear() {
        return this.sharedDataSqLiteHelper.clearAll();
    }


    public SharedDataEditor getSharedDataEditor() {
        if (sharedDataEditor == null) {
            sharedDataEditor = new SharedDataEditor();
        }

        sharedDataEditor.clearDatas();

        return sharedDataEditor;
    }

    public class SharedDataEditor {
        private HashMap<String, SharedData> sharedDataHashMap;

        public SharedDataEditor() {
            if (sharedDataHashMap == null) {
                sharedDataHashMap = new HashMap<String, SharedData>();
            } else {
                sharedDataHashMap.clear();
            }
        }

        public SharedDataEditor putString(String key, String value) {
            if (TextUtils.isEmpty(key)) {
                return this;
            }

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmStr(value);
            sharedData.setDataType(DataType.STRING);

            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public SharedDataEditor putBoolean(String key, boolean value) {
            if (TextUtils.isEmpty(key))
                return this;

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmBoolean(value);
            sharedData.setDataType(DataType.BOOLEAN);
            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public SharedDataEditor putInt(String key, int value) {
            if (TextUtils.isEmpty(key))
                return this;

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmInt(value);
            sharedData.setDataType(DataType.INT);

            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public SharedDataEditor putDate(String key, Date date) {
            if (TextUtils.isEmpty(key) || date == null)
                return this;

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmDate(date);
            sharedData.setDataType(DataType.DATA);

            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public SharedDataEditor putLong(String key, long value) {
            if (TextUtils.isEmpty(key))
                return this;

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmLong(value);
            sharedData.setDataType(DataType.LONG);
            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public SharedDataEditor putFloat(String key, float value) {
            if (TextUtils.isEmpty(key))
                return this;

            SharedData sharedData = getDefaultData();
            sharedData.setKey(key);
            sharedData.setmFloat(value);
            sharedData.setDataType(DataType.FLOAT);
            sharedDataHashMap.put(key, sharedData);

            return this;
        }

        public synchronized boolean commit() {
            boolean isSuccess = false;

            SharedDataSqLiteHelper sharedDataSqLiteHelper1 = sharedDataSqLiteHelper;
            HashMap<String, SharedData> copyOfsharedDataHashMap = (HashMap<String, SharedData>) sharedDataHashMap.clone();
            for (SharedData sharedData : copyOfsharedDataHashMap.values()) {
                if (sharedData != null) {
                    long id = sharedDataSqLiteHelper1.putData(sharedData);

                    isSuccess = id > 0;
                }
            }

            sharedDataHashMap.clear();

            return isSuccess;
        }

        /**
         */
        public void clearDatas() {
            if (sharedDataHashMap != null)
                sharedDataHashMap.clear();
        }
    }


    /**
     *
     * @return
     */
    private SharedData getDefaultData() {
        SharedData sharedData = new SharedData();
        sharedData.setmStr(null);
        sharedData.setmBoolean(false);
        sharedData.setmDate(null);
        sharedData.setmInt(-1);
        sharedData.setmLong(-1);
        sharedData.setmFloat(-1f);
        sharedData.setDataType(null);
        return sharedData;
    }
}
