package com.bridgeinternationalacademies.pupil.network;

import com.bridgeinternationalacademies.pupil.BuildConfig;
import com.bridgeinternationalacademies.pupil.callback.GenericCallback;
import com.bridgeinternationalacademies.pupil.model.Classroom;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by sushantchavan on 13/04/17.
 */

public class ServiceManager  {
    private static  OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new ServiceInterceptor());

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl(BuildConfig.API_BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson));

    Retrofit retrofit = builder.client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create()).build();

    PupilService client = retrofit.create(PupilService.class);

    public void getPupils(int num, final GenericCallback<Classroom> genericCallback ) {

        Call<Classroom> call = client.getListPupil(num);
        call.enqueue(new Callback<Classroom>() {
            @Override
            public void onResponse(Call<Classroom> call, Response<Classroom> response) {
               // List<Pupil> listofpupils = new ArrayList<Pupil>();
                if(response != null) {
                    Classroom newClassroom = response.body();
                    //listofpupils = (List<Pupil>) response.body();
                    genericCallback.onRequestSuccess(newClassroom);
                }
            }

            @Override
            public void onFailure(Call<Classroom> call, Throwable t) {
                t.getMessage();
            }
        });



    }
}
