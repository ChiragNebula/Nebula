package com.nebulacompanies.nebula.Network;

import static com.nebulacompanies.nebula.Config.NEB_API;

import android.content.Context;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sagar Virvani on 30-01-2018.
 */

public class APIClientNebula {
    private static final String TAG = "APIClientNebula";
    private static Retrofit retrofit = null;
    private static Context context;

    public static Retrofit getClient(final Context mContext) {

        context = mContext;
        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        //  Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.MINUTES)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(NEB_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


}
