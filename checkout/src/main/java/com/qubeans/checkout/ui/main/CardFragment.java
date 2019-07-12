package com.qubeans.checkout.ui.main;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qubeans.checkout.R;
import com.qubeans.checkout.utils.CardTextWatcher;
import com.qubeans.checkout.utils.CreditCardExpiryTextWatcher;
import com.qubeans.checkout.utils.CreditCardUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
    EditText etcardNumber, etExpiry, etCvv;
    CardTextWatcher cardTextWatcher;
    CreditCardExpiryTextWatcher cardExpiryTextWatcher;
    Button bPay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etcardNumber = view.findViewById(R.id.card_number_checkout);
        cardTextWatcher = new CardTextWatcher(etcardNumber);
        etcardNumber.addTextChangedListener(cardTextWatcher);

        etExpiry = view.findViewById(R.id.expiry_date);
        cardExpiryTextWatcher = new CreditCardExpiryTextWatcher(etExpiry);
        etExpiry.addTextChangedListener(cardExpiryTextWatcher);

        bPay = view.findViewById(R.id.bSubmit);
        bPay.setOnClickListener(v -> {
            submitPayment();
        });

    }

    private void submitPayment() {

        String expiryDate = etExpiry.getText().toString().trim();
        if (!CreditCardUtils.isValidDate(expiryDate) || TextUtils.isEmpty(expiryDate)){
            etExpiry.setError("Check this field");
            etExpiry.requestFocus();
            return;
        }

        String cardNumber = etcardNumber.getText().toString().trim();
        if (!CreditCardUtils.isValid(cardNumber) || TextUtils.isEmpty(cardNumber)){
            etcardNumber.setError("Enter a valid card Number");
            etcardNumber.requestFocus();
            return;
        }


        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }


}
