package com.qubeans.checkout.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hbb20.CountryCodePicker;
import com.qubeans.checkout.R;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileMoneyFragment extends Fragment {

    private CountryCodePicker cpp;
    private EditText etPhoneNumber, etfirstName, etlastName;
    Button bPay;
    private Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_money, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
