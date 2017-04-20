package com.bridgeinternationalacademies.pupil.network;

import java.net.SocketTimeoutException;

/**
 * Created by sushantchavan on 14/04/17.
 */

public class ServiceException extends SocketTimeoutException {
    String message;

    public ServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
