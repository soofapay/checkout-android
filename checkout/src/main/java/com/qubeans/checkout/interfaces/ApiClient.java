package com.qubeans.checkout.interfaces;

import com.qubeans.checkout.models.CheckNumber;
import com.qubeans.checkout.models.CheckTillResponse;
import com.qubeans.checkout.models.CheckoutResponse;
import com.qubeans.checkout.models.CheckoutTill;
import com.qubeans.checkout.models.PayData;
import com.qubeans.checkout.models.PayResponse;
import com.qubeans.checkout.models.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("soofa/pay-requests/")
    Call<CheckoutResponse> payRequest(@Body PayData payData);

    @POST("core/transactions/MB/")
    Call<CheckoutResponse> payWithMobile(@Body PayData payData);

    @POST("core/core/checkout-number/")
    Call<PayResponse> checkNumber(@Body CheckNumber checkNumber);

    @POST("business/till/checkout-till/")
    Call<CheckTillResponse> checkTill(@Body CheckoutTill checkoutTill);

    @GET("soofa/pay-requests/{id}")
    Call<TransactionResponse> checkResponse(@Path("id") String id);

    @POST("transactions/transactions/CB/")
    Call<CheckoutResponse> payWithCard(@Body PayData payData);

}

