package com.qubeans.checkout.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.qubeans.checkout.R;

import java.text.DecimalFormat;

public class CheckoutUtils {

    public static String  genRandomString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder builder = new StringBuilder(n);

        for (int i = 0; i <n ; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());

            builder.append(AlphaNumericString.charAt(index));
        }

        return builder.toString();
    }

    public static String formatToMoney(Double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);

    }


    public static void hideSoftKeyboard(Activity activity, View view) {
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
