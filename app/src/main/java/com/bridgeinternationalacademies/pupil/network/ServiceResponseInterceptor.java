package com.bridgeinternationalacademies.pupil.network;

import android.util.Log;

import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by sushantchavan on 14/04/17.
 */

public class ServiceResponseInterceptor implements Interceptor {
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
/*
            if(response.body() != null) {

                Log.i("jsonarry", stringJson);
                apiResponse = new JsonParser().parse(stringJson).getAsJsonObject();
                pupilarray = new JsonParser().parse(apiResponse.get("items").toString()).getAsJsonArray();
                newlistofPupil = new Gson().fromJson(pupilarray.toString(),Pupil[].class);

            }

*/


        return response;
    }
}
