package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckTillResponse implements Serializable {

    @SerializedName("till_name")
    @Expose
    private String tillName;
    @SerializedName("receiver")
    @Expose
    private Integer receiver;
    @SerializedName("till_no")
    @Expose
    private Integer tillNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("calling_code")
    @Expose
    private String callingCode;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    public CheckTillResponse(String tillName, Integer receiver, Integer tillNo, String amount,
                             String currency, String callingCode, String countryName, String countryCode) {
        this.tillName = tillName;
        this.receiver = receiver;
        this.tillNo = tillNo;
        this.amount = amount;
        this.currency = currency;
        this.callingCode = callingCode;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getTillName() {
        return tillName;
    }

    public void setTillName(String tillName) {
        this.tillName = tillName;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getTillNo() {
        return tillNo;
    }

    public void setTillNo(Integer tillNo) {
        this.tillNo = tillNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
