package com.qubeans.checkout.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.qubeans.checkout.R;

public class CheckoutBottomModal extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_modal_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvMobile, tvCard;

        View mobileView, cardView;

        mobileView = view.findViewById(R.id.mobile_layout);

        cardView = view.findViewById(R.id.card_layout);

        tvMobile = view.findViewById(R.id.tv_mobile);
        tvCard = view.findViewById(R.id.tv_card);

        tvMobile.setOnClickListener(v -> {
            tvMobile.setTextColor(getResources().getColor(R.color.colorAccentCheckout));
            tvCard.setTextColor(getResources().getColor(R.color.material_grey));
            mobileView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        });


        tvCard.setOnClickListener(v -> {
            tvCard.setTextColor(getResources().getColor(R.color.colorAccentCheckout));
            tvMobile.setTextColor(getResources().getColor(R.color.material_grey));

            mobileView.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);

        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }
}
