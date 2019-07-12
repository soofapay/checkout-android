package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobilePaymentOptions {

    @SerializedName("mpesa")
    @Expose
    private String mpesa;

    @SerializedName("soofa")
    @Expose
    private String soofa;

    public MobilePaymentOptions(String mpesa, String soofa) {
        this.mpesa = mpesa;
        this.soofa = soofa;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getSoofa() {
        return soofa;
    }

    public void setSoofa(String soofa) {
        this.soofa = soofa;
    }
}
