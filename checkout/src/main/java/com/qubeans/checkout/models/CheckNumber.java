package com.qubeans.checkout.models;

import com.google.gson.annotations.SerializedName;

public class CheckNumber {
    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("amount")
    private String amount;

    @SerializedName("till")
    private String tillNumber;

    public CheckNumber(String phoneNumer, String amount, String tillNumer) {
        this.phoneNumber = phoneNumer;
        this.amount = amount;
        this.tillNumber = tillNumer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTillNumber() {
        return tillNumber;
    }

    public void setTillNumber(String tillNumber) {
        this.tillNumber = tillNumber;
    }
}
