package com.qubeans.checkout.models;

import com.google.gson.annotations.SerializedName;

public class PayData {
    @SerializedName("data")
    private String data;

    public PayData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

