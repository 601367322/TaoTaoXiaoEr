package com.ttxr.share;


public enum DataType {
    STRING(0), BOOLEAN(1), INT(2), DATA(3), LONG(4), FLOAT(5);

    private int value;

    private DataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static DataType getDataTypeByValue(int value) {
        for (DataType type : DataType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
