package com.qubeans.checkout.models;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qubeans.checkout.R;
import com.qubeans.checkout.SoofaCheckout;
import com.qubeans.checkout.utils.CheckoutUtilsKt;

import java.io.Serializable;

/**
 * {@Author Reuben Bichage}
 * */

public class Soofa  implements Serializable {

    private static final String TAG  = "SOOFA";

    @SerializedName("till_no")
    @Expose
    private Integer tillNumber;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("reference")
    @Expose
    private String reference;

    private Activity activity;

    @SerializedName("domain")
    @Expose
    private String domain;

    private int theme = R.style.AppTheme;

    public static  final int TRANSACTION_SUCCESSFUL = 200;
    public static final int TRANSACTION_FAILED = 400;
    public static final int TRANSACTION_CANCELLED = 403;
    public static final int TRANSACTION_REQUEST_CODE =202;

    public Soofa(Activity activity, Integer tillNumber, Double amount, String reference) {
        this.tillNumber = tillNumber;
        this.amount = CheckoutUtilsKt.formatToMoney(amount);
        this.activity = activity;
        this.domain = activity.getApplicationContext().getPackageName();
        Log.e(TAG, "Soofa: " + this.domain);
        this.reference = reference;
    }

    public void create(){
        if(activity != null){
            Intent intent = new Intent(activity, SoofaCheckout.class);
            intent.putExtra("till_details", setTillDetails());
            intent.putExtra("reference", reference);
            activity.startActivityForResult(intent, TRANSACTION_REQUEST_CODE);
            System.out.println(TRANSACTION_REQUEST_CODE);

        }

        else {
            Log.e(TAG, "No activity found" );
        }

    }

    private CheckoutTill setTillDetails(){
        return new CheckoutTill(
                amount, tillNumber, domain
        );
    }

    public Soofa setTheme(int theme){
        this.theme = theme;
        return this;
    }


}
