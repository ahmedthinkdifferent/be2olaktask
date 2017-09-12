package com.ahmedagamy.task.core.placeslist;

import android.support.annotation.StringRes;

import com.ahmedagamy.task.core.mvp.BaseView;
import com.ahmedagamy.task.data.model.response.Place;

import java.util.List;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public interface PlacesContract {

    interface View extends BaseView {


        void showErrorDialog(@StringRes int text);

        void showNoInternetToast();

        void clearOldGooglePlaces();

        void showPlaces(List<Place> places);

        void showCannotLoadDataToast();

        void showGooglePlaces(List<Place> places);

    }

    interface Presenter {

        void loadPlaces();

        void filterPlaces(String filterText);


    }

    interface Interactor {
        void loadBey2olakPlaces();

        void filterPlaces(String filterText);

    }


    interface LoadPlacesType {
        int BEY2OLAK = 1, GOOGLE = 2;
    }

    interface LoadBey2olakPlacesListener {

        void loadBey2olakPlacesSuccess(List<Place> places);

        void loadBey2olakPlacesFail(Throwable throwable);
    }

    interface LoadGooglePlacesListener {

        void loadGooglePlacesSuccess(List<Place> places);

        void loadGooglePlacesFail(Throwable throwable);
    }

}
