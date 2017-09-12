package com.ahmedagamy.task.core.mvp;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public class BasePresenter<V extends BaseView> {

    public V view;

    public BasePresenter(V view){
        this.view = view;
    }


    public void attachView(V view){
        this.view =  view;
    }

    public void detachView(){
        this.view = null;
    }


    public boolean isDetachView(){
        return this.view == null;
    }


}
