package com.ahmedagamy.task.data.api;

/**
 * Created by ahmed agamy on 04/04/2017.
 */

public interface ConnectionListener<T> {

    void onSuccess(T response);

    void onFail(Throwable throwable);
}
