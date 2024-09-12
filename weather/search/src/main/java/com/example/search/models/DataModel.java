package com.example.search.models;


public class DataModel {
    private int code;
    private long timestamp;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "code=" + code +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                '}';
    }
}
