package com.ahmedagamy.task.core.placeslist;

import com.ahmedagamy.task.data.api.PlaceTypeConstant;
import com.ahmedagamy.task.data.model.response.Place;
import com.ahmedagamy.task.data.model.response.Prediction;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

 class PlacesMapper {


     static List<Place> mapPlaces(List<Prediction> predictions) {
        final List<Place> places = new ArrayList<>(predictions.size());
        rx.Observable.from(predictions).subscribe(new Action1<Prediction>() {
            @Override
            public void call(Prediction prediction) {
                Place place = new Place();
                place.setAddress(prediction.getDescription());
                place.setType(PlaceTypeConstant.GOOGLE_PLACE);
                places.add(place);
            }
        });
        return places;
    }
}
