package com.ahmedagamy.task.core.placeslist;

import android.content.Context;

import com.ahmedagamy.task.R;
import com.ahmedagamy.task.core.mvp.BasePresenter;
import com.ahmedagamy.task.data.model.response.Place;
import com.ahmedagamy.task.utils.NetworkUtils;

import java.util.List;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public class PlacesPresenter extends BasePresenter<PlacesContract.View> implements PlacesContract.Presenter,
        PlacesContract.LoadGooglePlacesListener, PlacesContract.LoadBey2olakPlacesListener {

    private final Context context;
    private PlacesInteractor placesInteractor;

    public PlacesPresenter(PlacesContract.View view, Context context) {
        super(view);
        this.context = context;
        placesInteractor = new PlacesInteractor(this, this);
    }

    @Override
    public void loadPlaces() {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            view.showNoInternetDialog();
            return;
        }
        view.showLoadingProgress();
        placesInteractor.loadBey2olakPlaces();
    }

    @Override
    public void filterPlaces(String filterText) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            view.showNoInternetToast();
            return;
        }
        placesInteractor.filterPlaces(filterText);
    }

    @Override
    public void loadBey2olakPlacesSuccess(List<Place> places) {
        if (isDetachView()) return;
        view.hideLoadingProgress();
        view.showPlaces(places);
    }

    @Override
    public void loadBey2olakPlacesFail(Throwable throwable) {
        if (isDetachView()) return;
        view.hideLoadingProgress();
        view.showErrorDialog(R.string.cannot_load_data);
    }

    @Override
    public void loadGooglePlacesSuccess(List<Place> places) {
        if (isDetachView()) return;
        view.clearOldGooglePlaces();
        view.showGooglePlaces(places);
    }

    @Override
    public void loadGooglePlacesFail(Throwable throwable) {
        if (isDetachView()) return;
        throwable.printStackTrace();
        view.showCannotLoadDataToast();
    }
}
