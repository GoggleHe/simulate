package com.example.simulate.bean;

public class BigObject {
    private byte[] data;

    public BigObject(int size) {
        this.data = new byte[size];
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
