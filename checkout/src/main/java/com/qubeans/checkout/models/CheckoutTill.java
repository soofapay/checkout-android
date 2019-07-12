package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckoutTill implements Serializable {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("till")
    @Expose
    private String till;
    @SerializedName("domain")
    @Expose
    private String domain;

    public CheckoutTill(String amount, Integer till, String domain) {
        this.amount = amount;
        this.till = String.valueOf(till);
        this.domain = domain;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
