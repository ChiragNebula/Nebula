package com.nebulacompanies.nebula.Network;

import static com.nebulacompanies.nebula.Config.TESTING_API;

import android.content.Context;

import com.nebulacompanies.nebula.util.SessionVacation;

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
 * Created by Palak Mehta on 06-Mar-18.
 */

public class APIClientCustomer {

    private static Retrofit retrofit = null;
    private static SessionVacation sessionVacation;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(final Context mContext) {
        sessionVacation = new SessionVacation(mContext);
        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        //  Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (sessionVacation.getCustomerLogin()) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.MINUTES)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder ongoing = chain.request().newBuilder();
                            ongoing.addHeader("Authorization", sessionVacation.getAccessTokenHoliday());
                            return chain.proceed(ongoing.build());
                        }
                    })
                    .addInterceptor(interceptor)
                    .connectTimeout(60, TimeUnit.MINUTES)
                    .cache(cache)
                    .build();
        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.MINUTES)
                    /*.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder ongoing = chain.request().newBuilder();
                            ongoing.addHeader("Authorization", sessionVacation.getAccessTokenHoliday());
                            return chain.proceed(ongoing.build());
                        }
                    })*/
                    .addInterceptor(interceptor)
                    .connectTimeout(60, TimeUnit.MINUTES)
                    .cache(cache)

                    .build();
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(TESTING_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static Retrofit getClientSite(final Context mContext) {


        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        //  Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.MINUTES)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://neb.hksolutions.in")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
