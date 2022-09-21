package com.nebulacompanies.nebula.Network;

import static com.nebulacompanies.nebula.Config.TESTING_API;

import android.content.Context;
import android.util.Log;

import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;

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
 * Create by : Jadav Chirag At Nebula Infra space LLP 28-09-2017
 * Modification by :
 */
public class APIClientCustomerBooking {

    private static final String TAG = "APIClient";
    private static Retrofit retrofit = null;
    public static Context context;
    private static UserAuthorization mUserAuthorization;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(final Context mContext) {

        context = mContext;
        mUserAuthorization = new UserAuthorization(mContext);

        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        //  Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(OFFLINE_INTERCEPTOR)
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .cache(cache)
                .readTimeout(60, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.MINUTES)
                .build();

        //if (mUserAuthorization.getUserLogin()) {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Authorization", mUserAuthorization.getAuthorizationToken());
                        return chain.proceed(ongoing.build());
                    }
                })
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.MINUTES)
                .cache(cache)
                .build();
        /*} else {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .connectTimeout(60, TimeUnit.MINUTES)
                    .cache(cache)
                    .build();*/
        retrofit = new Retrofit.Builder()
                .baseUrl(TESTING_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");

            if (AppUtils.isNetworkAvailable(context)) {
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") || cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 10)
                            .build();
                } else {
                    int maxAge = 60; // read from cache for 1 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    };

    private static final Interceptor OFFLINE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!AppUtils.isNetworkAvailable(context)) {
                Log.d(TAG, "Rewriting Request");
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return chain.proceed(request);
        }
    };
    // Custom Authorization Token Access.


}