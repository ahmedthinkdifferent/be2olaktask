package com.ahmedagamy.task.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmedagamy.task.R;
import com.ahmedagamy.task.core.placeslist.PlacesContract;
import com.ahmedagamy.task.data.model.response.Place;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ahmedagamy on 9/11/2017.
 */

public class PlaceAdapter extends BaseAdapter {

    private List<Place> places = new ArrayList<>();
    private List<Integer> sectionPositions = new ArrayList<>(1);

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Place getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (sectionPositions.isEmpty()) return 0;
        if (sectionPositions.contains(position)) {
            return 1; // section.
        }
        return 0;// item.
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        switch (getItemViewType(position)) {
            case 0:
                // item view.
                if (view == null) {
                    // inflate view.
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_place_item, viewGroup,
                            false);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    // get recycled view.
                    viewHolder = (ViewHolder) view.getTag();
                }
                // set data to views.
                viewHolder.placeTitle.setText(getItem(position).getAddress());
                break;
            default:
                // section view.
                if (view == null) {
                    view = LayoutInflater.from(viewGroup.getContext())
                            .inflate(R.layout.listview_section_item, viewGroup, false);
                }
        }

        return view;
    }


    public void addPlaces(List<Place> places) {
        this.places.addAll(0, places);
        notifyDataSetChanged();
    }

    public void addGooglePlaces(List<Place> places) {
        sectionPositions.clear();
        sectionPositions.add(places.size() - 1);
        addPlaces(places);
    }

    public void removeGooglePlaces() {
        List<Place> removedItems = new ArrayList<>();
        for (Place place : places) {
            if (place.getType() != PlacesContract.LoadPlacesType.GOOGLE) {
                // if place not google stop removing because all items in bottom of same type.
                break;
            }
            removedItems.add(place);
        }
        if (!removedItems.isEmpty()) {
            places.removeAll(removedItems);
            notifyDataSetChanged();
        }
    }

    private class ViewHolder {
        TextView placeTitle;

        ViewHolder(View itemView) {
            this.placeTitle = itemView.findViewById(R.id.place_title);
        }


    }
}
