package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayResponse {
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("mm_name")
    @Expose
    private String mmName;
    @SerializedName("provider_icon")
    @Expose
    private String providerIcon;
    @SerializedName("provider_has_b2c")
    @Expose
    private Boolean providerHasB2c;
    @SerializedName("provider_has_c2b")
    @Expose
    private Boolean providerHasC2b;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("phone_valid")
    @Expose
    private Boolean phoneValid;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("payment_options")
    @Expose
    public List<String> paymentOptions;

    public PayResponse(Integer providerId, String providerName, String mmName, String providerIcon, Boolean providerHasB2c,
                       Boolean providerHasC2b, String phone, Boolean phoneValid, String currency, List<String> paymentOptions) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.mmName = mmName;
        this.providerIcon = providerIcon;
        this.providerHasB2c = providerHasB2c;
        this.providerHasC2b = providerHasC2b;
        this.phone = phone;
        this.phoneValid = phoneValid;
        this.currency = currency;
        this.paymentOptions = paymentOptions;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getMmName() {
        return mmName;
    }

    public void setMmName(String mmName) {
        this.mmName = mmName;
    }

    public String getProviderIcon() {
        return providerIcon;
    }

    public void setProviderIcon(String providerIcon) {
        this.providerIcon = providerIcon;
    }

    public Boolean getProviderHasB2c() {
        return providerHasB2c;
    }

    public void setProviderHasB2c(Boolean providerHasB2c) {
        this.providerHasB2c = providerHasB2c;
    }

    public Boolean getProviderHasC2b() {
        return providerHasC2b;
    }

    public void setProviderHasC2b(Boolean providerHasC2b) {
        this.providerHasC2b = providerHasC2b;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPhoneValid() {
        return phoneValid;
    }

    public void setPhoneValid(Boolean phoneValid) {
        this.phoneValid = phoneValid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }
}
