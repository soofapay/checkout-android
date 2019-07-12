package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayRequest {
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("reference")
    @Expose
    private String reference;

    @SerializedName("till_no")
    @Expose
    private String tillNo;

    @SerializedName("checkout_token")
    @Expose
    private String checkoutToken;

    @SerializedName("request_amount")
    @Expose
    private String requestAmount;

    @SerializedName("domain")
    @Expose
    private String domain;



    public PayRequest(String phone, String firstName, String lastName, String checkoutToken, String requestAmount, String domain, String tillNo, String reference) {
        this.phone =phone;
        this.firstName=firstName;
        this.lastName = lastName;
        this.checkoutToken = checkoutToken;
        this.requestAmount =requestAmount;
        this.domain = domain;
        this.reference = reference;
        this.tillNo = tillNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTillNo() {
        return tillNo;
    }

    public void setTillNo(String tillNo) {
        this.tillNo = tillNo;
    }

    public String getCheckoutToken() {
        return checkoutToken;
    }

    public void setCheckoutToken(String checkoutToken) {
        this.checkoutToken = checkoutToken;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
