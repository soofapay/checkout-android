package com.qubeans.checkout.models;

import com.google.gson.annotations.SerializedName;

public class PayCard {
    @SerializedName("sender_account_no")  String cardNumber;
    @SerializedName("expiry")  String expiryDate;
    @SerializedName("cvv") String cvvValue;
    @SerializedName("country") String countryName;
    @SerializedName("postal_code") String postalAddress;
    @SerializedName("street") String streetAddress;
    @SerializedName("request_amount")  String requestAmount;
    @SerializedName("reference") String reference;
    @SerializedName("domain") String domainName;
    @SerializedName("till_no") String tillNumber;

    public PayCard(String cardNumber, String expiryDate, String cvvValue, String countryName, String postalAddress, String streetAddress, String requestAmount, String reference, String domainName, String tillNumber) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvvValue = cvvValue;
        this.countryName = countryName;
        this.postalAddress = postalAddress;
        this.streetAddress = streetAddress;
        this.requestAmount = requestAmount;
        this.reference = reference;
        this.domainName = domainName;
        this.tillNumber = tillNumber;
    }
}
