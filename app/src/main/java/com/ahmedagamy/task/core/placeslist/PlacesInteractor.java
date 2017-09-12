package com.ahmedagamy.task.core.placeslist;


import com.ahmedagamy.task.data.api.ApiNetwork;
import com.ahmedagamy.task.data.api.ConnectionListener;
import com.ahmedagamy.task.data.api.NetworkResponse;
import com.ahmedagamy.task.data.model.response.Bey2olakResponse;
import com.ahmedagamy.task.data.model.response.GooglePlacesResponse;
import com.ahmedagamy.task.data.model.response.Place;
import com.ahmedagamy.task.data.model.response.Prediction;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public class PlacesInteractor implements PlacesContract.Interactor, ConnectionListener<Bey2olakResponse> {


    private PlacesContract.LoadBey2olakPlacesListener bey2olakPlacesListener;
    private PlacesContract.LoadGooglePlacesListener loadGooglePlacesListener;
    private ApiNetwork apiNetwork;

    PlacesInteractor(PlacesContract.LoadBey2olakPlacesListener bey2olakPlacesListener, PlacesContract.LoadGooglePlacesListener loadGooglePlacesListener) {
        this.bey2olakPlacesListener = bey2olakPlacesListener;
        this.loadGooglePlacesListener = loadGooglePlacesListener;
        apiNetwork = new ApiNetwork();
    }


    @Override
    public void loadBey2olakPlaces() {
        loadBey3olakPlaces();
    }


    @Override
    public void filterPlaces(String filterText) {
        loadGooglePlaces(filterText);
    }


    private void loadBey3olakPlaces() {
        apiNetwork.getBey2olakPlaces(0, 30).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new NetworkResponse<Bey2olakResponse>(this));
    }

    private void loadGooglePlaces(String filterText) {
        apiNetwork.getPlacesByGoogle(filterText).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkResponse<>(new ConnectionListener<GooglePlacesResponse>() {
                    @Override
                    public void onSuccess(GooglePlacesResponse response) {
                        List<Prediction> predictions = response.getPredictions();
                        List<Place> places = PlacesMapper.mapPlaces(predictions);
                        loadGooglePlacesListener.loadGooglePlacesSuccess(places);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                     loadGooglePlacesListener.loadGooglePlacesFail(throwable);
                    }
                }));
    }


    @Override
    public void onSuccess(Bey2olakResponse response) {
        List<Place> places = response.getContent();
        bey2olakPlacesListener.loadBey2olakPlacesSuccess(places);
    }

    @Override
    public void onFail(Throwable throwable) {
        bey2olakPlacesListener.loadBey2olakPlacesFail(throwable);
    }
}
