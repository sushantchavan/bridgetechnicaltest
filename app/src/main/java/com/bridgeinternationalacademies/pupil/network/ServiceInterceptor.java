package com.bridgeinternationalacademies.pupil.network;

import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sushantchavan on 24/04/17.
 */

public class ServiceInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        JsonObject apiResponse = null;
        JsonArray pupilarray = null;
        JSONObject items = null;
        Pupil[] newlistofPupil=null;
        try {
            response = chain.proceed(request);
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
            throw new ServiceException("Something went wrong");
        }

        return response;
    }
}
