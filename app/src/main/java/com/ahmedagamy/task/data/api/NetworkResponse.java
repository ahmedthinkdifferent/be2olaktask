package com.ahmedagamy.task.data.api;

import rx.Observer;

/**
 * Created by xps on 09/08/2017.
 */

public class NetworkResponse<T> implements Observer<T> {

    private ConnectionListener<T> listener;

    public NetworkResponse(ConnectionListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        listener.onFail(e);
    }

    @Override
    public void onNext(T response) {
        listener.onSuccess(response);
    }
}
