package com.ahmedagamy.task.data.api;

import android.support.annotation.StringRes;

import com.ahmedagamy.task.data.model.response.Bey2olakResponse;
import com.ahmedagamy.task.data.model.response.GooglePlacesResponse;


import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public interface ApiEndPoint {


    @GET("places")
    Observable<Bey2olakResponse> getPlaces(@Query("page") int page, @Query("size") int size);

    @POST("json")
    Observable<GooglePlacesResponse> getPlacesByGoogle(@Query("key") String key ,
                                                       @Query("components")String component , @Query("input") String input);

}
