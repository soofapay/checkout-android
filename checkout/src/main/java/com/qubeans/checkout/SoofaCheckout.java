package com.qubeans.checkout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbb20.CountryCodePicker;
import com.qubeans.checkout.api.MobileClient;
import com.qubeans.checkout.api.RetrofitClient;
import com.qubeans.checkout.interfaces.ApiClient;
import com.qubeans.checkout.models.CheckNumber;
import com.qubeans.checkout.models.CheckTillResponse;
import com.qubeans.checkout.models.CheckoutResponse;
import com.qubeans.checkout.models.CheckoutTill;
import com.qubeans.checkout.models.PayCard;
import com.qubeans.checkout.models.PayData;
import com.qubeans.checkout.models.PayRequest;
import com.qubeans.checkout.models.PayResponse;
import com.qubeans.checkout.models.Soofa;
import com.qubeans.checkout.models.TransactionResponse;
import com.qubeans.checkout.utils.CardTextWatcher;
import com.qubeans.checkout.utils.CheckoutUtilsKt;
import com.qubeans.checkout.utils.CreditCardExpiryTextWatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cdflynn.android.library.checkview.CheckView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class SoofaCheckout extends AppCompatActivity {
    private EditText etPhoneNumber, etFirstName, etLastName;
    private Retrofit retrofit;
    CheckoutTill checkoutTill;
    EditText etcardNumber, etExpiry, etCvv;
    CardTextWatcher cardTextWatcher;
    CreditCardExpiryTextWatcher cardExpiryTextWatcher;
    TextView tvMobile, tvCard, tvWait;
    LinearLayout titleLayout;
    TextView instructions;
    ProgressBar progressBar;
    ProgressBar mobileProgress;
    CardView mainCard;
    private static final String TAG  = "SOOFA";
    View mobileView, cardView, stkView,  successView;
    CheckView checkView;
    TextView businessName;
    CountryCodePicker cpp;
    Button bPay, bPayCard;
    RadioGroup radioGroup;
    RadioButton radioSoofa;
    RadioButton radioMpesa;
    String reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_checkout);
        overridePendingTransition(R.anim.go_in, R.anim.go_out);

        progressBar = findViewById(R.id.progressBar2);
        mobileProgress = findViewById(R.id.mobile_progress);
        mainCard = findViewById(R.id.details_card);
        mainCard.setCardBackgroundColor(getResources().getColor(R.color.white_smoke));

        mainCard.setBackgroundResource(R.drawable.card_shape);
        etCvv = findViewById(R.id.card_cvv);

        businessName = findViewById(R.id.tv_business_name);
        bPay = findViewById(com.qubeans.checkout.R.id.b_submit_pay);
        bPayCard = findViewById(R.id.bSubmit);
        tvWait = findViewById(R.id.tv_wait);

        radioGroup = findViewById(R.id.radio_group);
        radioSoofa = findViewById(R.id.radioSoofa);
        radioMpesa = findViewById(R.id.radioMobile);
        radioMpesa.setChecked(true);


        checkoutTill = (CheckoutTill) getIntent().getExtras().getSerializable("till_details");

        reference = getIntent().getStringExtra("reference");

        mobileView = findViewById(com.qubeans.checkout.R.id.mobile_layout);
        cardView = findViewById(com.qubeans.checkout.R.id.card_layout);
        stkView = findViewById(R.id.instructions_layout);
        titleLayout = findViewById(R.id.title_layout);
        successView = findViewById(R.id.success_layout);

        checkView = findViewById(R.id.check);

        instructions = findViewById(R.id.tv_instructions);
        checkTillResponse();
    }

    private void checkTillResponse() {

        Call<CheckTillResponse> call = RetrofitClient.getInstance().getApi().checkTill(checkoutTill);
        call.enqueue(new Callback<CheckTillResponse>() {

            @Override
            public void onResponse(Call<CheckTillResponse> call, Response<CheckTillResponse> response) {
                CheckTillResponse checkTillResponse = response.body();

                if (response.code()==200){
                    progressBar.setVisibility(View.GONE);
                    mainCard.setVisibility(View.VISIBLE);
                    businessName.setText(checkTillResponse.getTillName());
                    bPay.setText(String.format("Pay  %s %s", checkTillResponse.getCurrency(), checkTillResponse.getAmount()));

                    showCard(String.format("Pay  %s %s", checkTillResponse.getCurrency(), checkTillResponse.getAmount()));
                }

                else  if (response.code() == 404){
                    throw new IllegalArgumentException("Invalid Till Number");
//                        Toast.makeText(SoofaCheckout.this, "check log for details", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.VISIBLE);
//                        Intent intent = new Intent();
//                        String message = "Check your till number";
//                        intent.putExtra("message", message);
//                        setResult(Soofa.TRANSACTION_FAILED, intent);
//                        finish();
                    }
                else if  (response.code() == 400){
                    throw new  IllegalStateException(String.format("This package is not registered yet, got to your dashboard and add  '%s'  to the list of your ALLOWED HOSTS", getApplicationContext().getPackageName()));

//                    Log.e(TAG, " This package is not registered yet, got to your dashboard and add " + getApplicationContext().getPackageName() + " to the list of your ALLOWED HOSTS");
//                        progressBar.setVisibility(View.VISIBLE);
//
//                        Intent intent = new Intent();
//                        String message = " This package is not registered yet, got to your dashboard and add " + getApplicationContext().getPackageName() + " to the list of your ALLOWED HOSTS";
//                        intent.putExtra("message", message);
//                        setResult(Soofa.TRANSACTION_FAILED, intent);
//                        finish();
                    }

                else{
                        progressBar.setVisibility(View.VISIBLE);
                        finish();
                    }
                }


            @Override
            public void onFailure(Call<CheckTillResponse> call, Throwable t) {
                Toast.makeText(SoofaCheckout.this, "Check your connection", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void showCard(String amount) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {

                    Request auth = chain.request();
                    Request authRequest = auth.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .method(auth.method(), auth.body())
                            .build();

                    return chain.proceed(authRequest);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.qubeans.com/")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        tvMobile = findViewById(com.qubeans.checkout.R.id.tv_mobile);
        tvMobile.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimaryCheckout));
        tvMobile.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparent));
        tvCard = findViewById(com.qubeans.checkout.R.id.tv_card);
        tvCard.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.material_grey));

        etPhoneNumber = findViewById(com.qubeans.checkout.R.id.et_phone);
        cpp = findViewById(com.qubeans.checkout.R.id.country_code);
        cpp.registerCarrierNumberEditText(etPhoneNumber);

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                postText(s, start, count, after);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postText(s, start, count, before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()<3){
                    radioGroup.setVisibility(View.GONE);
                    radioMpesa.setVisibility(View.GONE);
                    radioSoofa.setVisibility(View.GONE);
                    etPhoneNumber.setCompoundDrawables(null, null, null, null);
                }
            }
        });

        etFirstName = findViewById(com.qubeans.checkout.R.id.et_first_name);
        etLastName = findViewById(com.qubeans.checkout.R.id.et_last_name);

        bPay.setOnClickListener(v -> {
            String phone_1 = etPhoneNumber.getText().toString().trim();

            if (phone_1.startsWith("0")) {
                etPhoneNumber.setText(etPhoneNumber.getText().delete(0, 1));

            }

            final String phone = String.format("%s%s", cpp.getSelectedCountryCode(), etPhoneNumber.getText().toString().trim());

            if (!cpp.isValidFullNumber()){
                etPhoneNumber.setError("Please check your number");
                etPhoneNumber.requestFocus();
                return;
            }

            String firstName = etFirstName.getText().toString().trim();
            if (TextUtils.isEmpty(firstName)){
                etFirstName.setError("This is required");
                etFirstName.requestFocus();
                return;
            }

            String lastName = etLastName.getText().toString().trim();
            if (TextUtils.isEmpty(lastName)){
                etLastName.setError("This is required");
                etLastName.requestFocus();
                return;
            }
            CheckoutUtilsKt.hideSoftKeyboard(this, getCurrentFocus());

            Date date = new Date();
            String checkoutToken = CheckoutUtilsKt.genRandomString(20) + "-" + date.getTime();

            String requestAmount = String.valueOf(checkoutTill.getAmount());

            if (radioGroup.getCheckedRadioButtonId()==R.id.radioSoofa){
                payWithSoofa(phone, firstName, lastName, checkoutToken, requestAmount, getPackageName());

            }

            else {
                payWithMobile(phone, firstName, lastName, checkoutToken, requestAmount, getPackageName(), reference);
                Log.e(TAG, "showCard: " + getPackageName());
            }

        });

        tvMobile.setOnClickListener(v -> {
            tvMobile.setTextColor(getApplicationContext().getResources().getColor(R.color.material_grey));
            tvCard.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimaryCheckout));
            tvCard.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.material_grey));
            tvMobile.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparent));
            mobileView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        });

        tvCard.setOnClickListener(v -> {
            tvCard.setTextColor(getApplicationContext().getResources().getColor(R.color.material_grey));
            tvMobile.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimaryCheckout));
            tvMobile.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.material_grey));
            tvCard.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparent));

            mobileView.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
            bPayCard.setText(amount);

            bPayCard.setOnClickListener(v1 -> {
                String cardNumber = etcardNumber.getText().toString().trim().replace(" ", "");
                if (cardNumber.isEmpty() || cardNumber.length()<16){
                    etcardNumber.setError("check you card");
                    etcardNumber.requestFocus();
                    return;
                }

                String expiryDate = etExpiry.getText().toString().trim();
                if (expiryDate.isEmpty()){
                    etExpiry.setError("check this");
                    etExpiry.requestFocus();
                    return;
                }

                String cvvValue = etCvv.getText().toString().trim();
                if (cvvValue.isEmpty()){
                    etCvv.setError("check this");
                    etCvv.requestFocus();
                    return;
                }

                EditText etCountry = findViewById(R.id.etCountry);
                String countryName = etCountry.getText().toString().trim();
                if (countryName.isEmpty()){
                    etCountry.setError("check this");
                    etCountry.requestFocus();
                    return;
                }

                EditText etPostal = findViewById(R.id.postalAddress);
                String postalAddress = etPostal.getText().toString().trim();
                if (postalAddress.isEmpty()){
                    etPostal.setError("check this");
                    etPostal.requestFocus();
                    return;
                }

                EditText etStreet = findViewById(R.id.etStreet);
                String streetAddress = etStreet.getText().toString().trim();
                if (streetAddress.isEmpty()){
                    etStreet.setError("check this");
                    etStreet.requestFocus();
                    return;
                }

                payWithCard(cardNumber, expiryDate, cvvValue, countryName, postalAddress, streetAddress, getPackageName(), checkoutTill.getAmount(), "MacBook Pro");





            });

        });

        //card details

        etcardNumber = findViewById(R.id.card_number_checkout);
        cardTextWatcher = new CardTextWatcher(etcardNumber);
        etcardNumber.addTextChangedListener(cardTextWatcher);

        etExpiry = findViewById(R.id.expiry_date);
        cardExpiryTextWatcher = new CreditCardExpiryTextWatcher(etExpiry);
        etExpiry.addTextChangedListener(cardExpiryTextWatcher);

    }

    private void postText(CharSequence s, int start, int count, int after) {
        if (count>=3){
            String phone_1 = etPhoneNumber.getText().toString();
            if (phone_1.startsWith("0")){
//                        etPhoneNumber.setText(etPhoneNumber.getText().delete(0, 1));
                phone_1 = phone_1.substring(1);

            }
            String phoneNumber = cpp.getSelectedCountryCode() + phone_1;
            Log.e("Country code", "onTextChanged: " + cpp.getSelectedCountryCode() );


            CheckNumber checkNumber = new CheckNumber(phoneNumber.replace(" ", ""), checkoutTill.getAmount(), checkoutTill.getTill());

            Call<PayResponse> call = retrofit.create(ApiClient.class).checkNumber(checkNumber);
            call.enqueue(new Callback<PayResponse>() {
                @Override
                public void onResponse(Call<PayResponse> call, Response<PayResponse> response) {
                    PayResponse payResponse = response.body();

                    if (response.isSuccessful()){
                        List<String> paymentOptions = payResponse.getPaymentOptions();
                        Glide.with(getApplicationContext())
                                .load(payResponse.getProviderIcon())
                                .into(new CustomTarget<Drawable>(20,36) {
                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                        etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                        etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, placeholder, null);
                                    }
                                });
                        Log.e("Payment Options", "onResponse: " + paymentOptions);

                        if (paymentOptions.size()==2){
                            String mobilePayment = paymentOptions.get(0);
                            radioSoofa.setChecked(true);

                            if (mobilePayment.equals("soofa")){
                                mobilePayment = paymentOptions.get(1);
                            }
                            radioMpesa.setText(mobilePayment);
                        }

                        if (paymentOptions.size()==1){
                            if (paymentOptions.get(0).equals("soofa")) {
                                radioSoofa.setChecked(true);
                            }

                            else {
                                radioSoofa.setVisibility(View.GONE);
                                radioMpesa.setVisibility(View.GONE);

                            }
                        }
                        else {
                            radioGroup.setVisibility(View.VISIBLE);
                            radioMpesa.setVisibility(View.VISIBLE);
                            radioSoofa.setVisibility(View.VISIBLE);
                        }

                    }

                    else {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            Log.e("Phone Number", "error: " + jsonObject.getString("error") );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PayResponse> call, Throwable t) {
                    t.printStackTrace();

                }
            });

        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog);
        builder.setMessage(getResources().getString(R.string.back_warning));
        builder.setPositiveButton("YES", (dialogInterface, i) -> {
            Intent intent = new Intent();
            intent.putExtra("message", "Transaction cancelled by user");
            setResult(Soofa.TRANSACTION_CANCELLED, intent);
            finish();
            overridePendingTransition(R.anim.go_in, R.anim.go_out);
        });

        builder.setNegativeButton("NO",  (dialogInterface, i) ->
                dialogInterface.dismiss());
        builder.show();
    }

    private void payWithSoofa(String phone, String firstName, String lastName, String checkoutToken, String requestAmount, String domainName) {

        PayRequest payRequest = new PayRequest(phone.replace(" ", ""), firstName, lastName, checkoutToken, requestAmount, domainName, checkoutTill.getTill(), reference);
        mobileProgress.setVisibility(View.VISIBLE);
        bPay.setVisibility(View.GONE);

        Gson gson = new Gson();
        String payJson= gson.toJson(payRequest);
        String data = CheckoutUtilsKt.encryptData(payJson);

        Call<CheckoutResponse> call = MobileClient.getInstance().getApi().payRequest(new PayData(data));
        call.enqueue(new Callback<CheckoutResponse>() {
            @Override
            public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                CheckoutResponse checkoutResponse = response.body();
                if (response.isSuccessful()) {
                    mobileProgress.setVisibility(View.GONE);
                    String instructionsText = checkoutResponse.getaInstructions();
                    Log.e("Checkout", "Instructions" + instructionsText);
                    instructions.setText(checkoutResponse.getaInstructions().replace("\\n", "\n"));
                    stkView.setVisibility(View.VISIBLE);
                    mobileView.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    titleLayout.setVisibility(View.GONE);
                    tvWait.setText("Wait for A Soofa Notification");

                    checkSoofaResponse(checkoutResponse.getTransactionId());
                }
                else {
                    Toast.makeText(SoofaCheckout.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
                    mobileProgress.setVisibility(View.GONE);
                    bPay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CheckoutResponse> call, Throwable t) {
                mobileProgress.setVisibility(View.GONE);
                bPay.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Unable to connect, try again later.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });
    }

    private void checkSoofaResponse(String transactionId) {
        new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long l) {
                Call<TransactionResponse> call = MobileClient.getInstance().getApi().checkResponse(transactionId);
                call.enqueue(new Callback<TransactionResponse>() {
                    @Override
                    public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {

                        TransactionResponse transactionResponse = response.body();
                        Log.e(TAG,  transactionResponse.getType() );

                        String trasactionId = transactionResponse.getPayload().getTid();
                              switch (transactionResponse.getType()){

                                  case "TRANSACTION_SUCCESSFUL":

                                      stkView.setVisibility(View.GONE);
                                      successView.setVisibility(View.VISIBLE);
                                      checkView.check();
                                      Log.e(TAG, "onResponse: " + transactionId );
                                      Intent intent = new Intent();
                                      intent.putExtra("tid", transactionId );
                                      intent.putExtra("reference", reference);
                                      setResult(Soofa.TRANSACTION_SUCCESSFUL, intent);
                                      cancel();
                                     new Handler().postDelayed(() -> {
                                         finish();
                                     }, 2000);

                                      break;

                                  case "TRANSACTION_CANCELLED":
                                       intent = new Intent();
                                      intent.putExtra("message", "Transaction cancelled by user");
                                      setResult(Soofa.TRANSACTION_CANCELLED, intent);
                                      finish();
                                      overridePendingTransition(R.anim.go_in, R.anim.go_out);
                                      cancel();
                                      finish();
                                      break;
                              }

                    }

                    @Override
                    public void onFailure(Call<TransactionResponse> call, Throwable t) {
                        Toast.makeText(SoofaCheckout.this, "Unable to connect, check your connection", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.putExtra("message", "Transaction timed out");
                setResult(Soofa.TRANSACTION_CANCELLED, intent);
                finish();
                overridePendingTransition(R.anim.go_in, R.anim.go_out);
                finish();
            }
        }.start();
    }

    private void payWithMobile(String phone, String firstName, String lastName, String checkoutToken, String requestAmount, String domainName, String reference) {
        PayRequest payRequest = new PayRequest(phone.replace(" ", ""), firstName, lastName, checkoutToken, CheckoutUtilsKt.formatToMoney(Double.parseDouble(requestAmount)), domainName, checkoutTill.getTill(), reference);
        Log.e(TAG, "Domain name: " + domainName );

        mobileProgress.setVisibility(View.VISIBLE);
        bPay.setVisibility(View.GONE);

        Gson gson = new Gson();
        String payJson= gson.toJson(payRequest);
        String data = CheckoutUtilsKt.encryptData(payJson);

        Call<CheckoutResponse> call = MobileClient.getInstance().getApi().payWithMobile(new PayData(data));
        call.enqueue(new Callback<CheckoutResponse>() {
            @Override
            public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                CheckoutResponse checkoutResponse = response.body();
                if (response.isSuccessful()) {
                    mobileProgress.setVisibility(View.GONE);
                    String instructionsText = checkoutResponse.getaInstructions();
                    Log.e("Checkout", "Instructions" + instructionsText);
                    instructions.setText(checkoutResponse.getaInstructions().replace("\\n", "\n"));
                    stkView.setVisibility(View.VISIBLE);
                    mobileView.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    titleLayout.setVisibility(View.GONE);

                }
                else {

                    //TODO: simulate errors

                    Toast.makeText(SoofaCheckout.this, "Unable to connect, try again later.", Toast.LENGTH_SHORT).show();
                    mobileProgress.setVisibility(View.GONE);
                    bPay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CheckoutResponse> call, Throwable t) {

                mobileProgress.setVisibility(View.GONE);
                bPay.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Unable to connect, try again later.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void payWithCard(String cardNumber, String expiry, String cvvCode, String country, String postal, String street, String domainName, String requestAmount, String reference){

        PayCard payCard = new PayCard(cardNumber, expiry, cvvCode, country, postal, street, requestAmount, reference, domainName, checkoutTill.getTill());

        Gson gson = new Gson();
        String modelToString = gson.toJson(payCard);

        PayData payData = new PayData(CheckoutUtilsKt.encryptData(modelToString));

        RetrofitClient.getInstance()
                .getApi()
                .payWithCard(payData)
                .enqueue(new Callback<CheckoutResponse>() {
                    @Override
                    public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                        if (response.code() ==200){
                            Toast.makeText(SoofaCheckout.this, "Successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckoutResponse> call, Throwable t) {

                    }
                });

    }

}