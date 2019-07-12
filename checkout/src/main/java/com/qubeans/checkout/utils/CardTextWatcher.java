package com.qubeans.checkout.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
* Formats the entered text in credit card format
* e.g 1234-5678-0123
* */

public class CardTextWatcher implements TextWatcher {

    private EditText etCard;
    private TextView tvCard;
    private ImageView ivType;
    private boolean isDelete;
    CreditCardType creditCardType;

    public CardTextWatcher(EditText etcard, TextView tvcard) {
        this.etCard=etcard;
        this.tvCard=tvcard;
    }

    public CardTextWatcher(EditText etcard, TextView tvcard, CreditCardType creditCardType) {
        this.etCard=etcard;
        this.tvCard=tvcard;
        this.creditCardType=creditCardType;
    }

    public CardTextWatcher(EditText etcard, TextView tvcard, ImageView ivType, CreditCardType creditCardType) {
        this.etCard=etcard;
        this.tvCard=tvcard;
        this.ivType=ivType;
        this.creditCardType=creditCardType;
    }


    public CardTextWatcher(EditText etcard) {
        this.etCard=etcard;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (count>=1 && count<=3){
            String source = s.toString();
            CreditCardUtils.getCardType(source);
            Log.e("Card Type", "beforeTextChanged: " + CreditCardUtils.getCardType(source));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isDelete= before != 0;
    }

    @Override
    public void afterTextChanged(Editable s) {
        String source = s.toString();
        int length=source.length();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(source);

        if(length>0 && length%5==0)
        {
            if(isDelete)
                stringBuilder.deleteCharAt(length-1);
            else
                stringBuilder.insert(length-1," ");
            etCard.setText(stringBuilder);
            etCard.setSelection(etCard.getText().length());

            Log.e("Credit Card", "afterTextChanged: " + CreditCardUtils.getCardType(source));
        }

        if(length>=4&&creditCardType!=null)
            creditCardType.setCardType(CreditCardUtils.getCardType(source.trim()));
        Log.e("Card Watcher", "afterTextChanged: " + CreditCardUtils.getCardType(source.trim()));

        if(tvCard!=null)
        {
            if(length==0)
                tvCard.setText("XXXX XXXX XXXX XXXX");
            else
                tvCard.setText(stringBuilder);
        }

        if(ivType!=null&&length==0)
            ivType.setImageResource(android.R.color.transparent);

    }

    public interface CreditCardType
    {
        public void setCardType(int type);
    }

}
