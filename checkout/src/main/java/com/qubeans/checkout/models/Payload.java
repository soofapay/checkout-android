package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class Payload implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("checkout_token")
    @Expose
    private String checkoutToken;

    public Payload(Integer id, String tid, String checkoutToken) {
        this.id = id;
        this.tid = tid;
        this.checkoutToken = checkoutToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCheckoutToken() {
        return checkoutToken;
    }

    public void setCheckoutToken(String checkoutToken) {
        this.checkoutToken = checkoutToken;
    }
}
