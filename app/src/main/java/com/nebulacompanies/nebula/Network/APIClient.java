package com.nebulacompanies.nebula.Network;

import static com.nebulacompanies.nebula.Config.TESTING_API;

import android.content.Context;

import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.util.Session;
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
 * Class : APIClient
 * Details:
 * Create by : Palak Mehta At Nebula Infra space LLP 09-01-2018
 * Modification by :
 */
public class APIClient {

    private static final String TAG = "APIClient";
    private static Retrofit retrofit = null;
    private static Context context;
    private static Session session;
    private static OkHttpClient okHttpClient;
    private static SessionVacation sessionVacation;

    public static Retrofit getClient(final Context mContext) {

        context = mContext;
        session = new Session(mContext);
        sessionVacation = new SessionVacation(mContext);

        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        //  Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        if (session.getLogin()) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.MINUTES)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder ongoing = chain.request().newBuilder();
                            ongoing.addHeader("Authorization", session.getAccessToken());
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
                   /* .addInterceptor(new Interceptor() {
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


    // Custom Authorization Token Access.

    public static Retrofit getClientSite(final Context mContext) {

        context = mContext;
        session = new Session(mContext);

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
