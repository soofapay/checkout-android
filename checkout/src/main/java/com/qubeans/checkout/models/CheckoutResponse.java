package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutResponse {

    @SerializedName("a_instructions")
    @Expose
    private String aInstructions;
    @SerializedName("TransactionId")
    @Expose
    private String transactionId;


    public CheckoutResponse( String aInstructions, String transactionId, String tid) {
        this.aInstructions = aInstructions;
        this.transactionId = transactionId;
    }




    public String getaInstructions() {
        return aInstructions;
    }

    public void setaInstructions(String aInstructions) {
        this.aInstructions = aInstructions;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
