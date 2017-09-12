package com.ahmedagamy.task.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ahmedagamy.task.R;
import com.ahmedagamy.task.core.placeslist.PlacesContract;
import com.ahmedagamy.task.core.placeslist.PlacesPresenter;
import com.ahmedagamy.task.data.model.response.Place;
import com.ahmedagamy.task.ui.adapter.PlaceAdapter;
import com.ahmedagamy.task.utils.DialogUtils;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class PlacesListActivity extends AppCompatActivity implements PlacesContract.View {

    //GUI.
    private ListView placesList;
    private SpotsDialog spotsDialog;
    //Objects.
    private PlacesPresenter placesPresenter;
    private PlaceAdapter placeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        initView();
        placesPresenter = new PlacesPresenter(this, this);
        placesPresenter.loadPlaces();
    }

    private void initView() {
        placesList = findViewById(R.id.places_list);
        placeAdapter = new PlaceAdapter();
        placesList.setAdapter(placeAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_places_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Toast like print
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String place) {
                    placesPresenter.filterPlaces(place);
                    return true;
                }
            });
        }


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void showNoInternetDialog() {
        DialogUtils.showNoInternetDialog(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public void showLoadingProgress() {
        spotsDialog = DialogUtils.createProgress(this);
        spotsDialog.show();
    }

    @Override
    public void hideLoadingProgress() {
        if (spotsDialog != null) {
            spotsDialog.dismiss();
        }
    }


    @Override
    public void showErrorDialog(int text) {
        DialogUtils.showErrorDialog(this, R.string.cannot_load_data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearOldGooglePlaces() {
        placeAdapter.removeGooglePlaces();
    }

    @Override
    public void showPlaces(List<Place> places) {
        placeAdapter.addPlaces(places);
    }

    @Override
    public void showCannotLoadDataToast() {
        Toast.makeText(this, getString(R.string.cannot_load_data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGooglePlaces(List<Place> places) {
        placeAdapter.addGooglePlaces(places);
    }
}
