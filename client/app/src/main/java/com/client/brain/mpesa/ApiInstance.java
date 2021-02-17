package com.client.brain.mpesa;

import com.google.android.gms.common.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://mpesa-payment-bridge.herokuapp.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
