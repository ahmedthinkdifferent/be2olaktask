package com.ahmedagamy.task.data.api;

import com.ahmedagamy.task.BuildConfig;
import com.ahmedagamy.task.data.model.response.Bey2olakResponse;
import com.ahmedagamy.task.data.model.response.GooglePlacesResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public class ApiNetwork {


    private Retrofit retrofit(String link) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();
        // Retrofit.
        return new Retrofit.Builder()
                .baseUrl(link)
                .client(client).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public rx.Observable<Bey2olakResponse> getBey2olakPlaces(int pageNumber, int size) {
        return retrofit(ApiConstant.BYQOLAK_LINK).create(ApiEndPoint.class).getPlaces(pageNumber, size);
    }

    public rx.Observable<GooglePlacesResponse> getPlacesByGoogle(String place) {
        return retrofit(ApiConstant.GOOGLE_LINK).create(ApiEndPoint.class)
                .getPlacesByGoogle(ApiConstant.SERVER_KEY, "country:eg", place);
    }
}
