package com.bridgeinternationalacademies.pupil.callback;

/**
 * Created by sushantchavan on 17/04/17.
 */

public interface GenericCallback <T> {
    void onRequestSuccess(T objectToReturn);
    void onRequestFailure(Throwable error, String errorMessage);
}
