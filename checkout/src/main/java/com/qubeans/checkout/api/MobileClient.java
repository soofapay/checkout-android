package com.qubeans.checkout.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qubeans.checkout.interfaces.ApiClient;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MobileClient {
    private Retrofit retrofit;
    private static  final String BASE_URL = "https://mobile.soofapay.com/";
    private String qdid;
    private Context context;

    @SuppressLint("HardwareIds")
    private MobileClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .create();


        String TAG = "Mobile Request";
        Log.e(TAG, "MobileClient: " + qdid );
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {

                    Request auth = chain.request();
                    Request authRequest = auth.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("qdid", "randoiqeocnwoicnwe")
                            .method(auth.method(), auth.body())
                            .build();

                    return chain.proceed(authRequest);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized MobileClient getInstance(){
        MobileClient mobileClient;
        mobileClient = new MobileClient();
        return mobileClient;
    }

    public ApiClient getApi(){
        return retrofit.create(ApiClient.class);
    }

}
