package com.qubeans.checkout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransactionResponse  implements Serializable {
    @SerializedName("type")
   @Expose
    private String type;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public TransactionResponse(String type, Payload payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
