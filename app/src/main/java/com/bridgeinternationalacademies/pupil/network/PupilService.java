package com.bridgeinternationalacademies.pupil.network;

import com.bridgeinternationalacademies.pupil.model.Classroom;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sushantchavan on 13/04/17.
 */

public interface PupilService {
    @GET("/api/pupils")
    Call<Classroom> getListPupil(@Query("page") int num);

    @FormUrlEncoded
    @POST("/api/pupils")
    Call<JSONObject> createPupil(@Body Pupil pupil);
    @DELETE("/api/pupils/{pupilId}")
    Call<JSONObject> deletePupil(
            @Path("pupilId") String pupilId
    );

    @GET("/api/pupils/{pupilId}")
    Call<JSONObject> getPupil(
            @Path("pupilId") String pupilId
    );

    @PUT("/api/pupils/{pupilId")
    Call<JSONObject> updatePupil(
            @Path("pupilId") String pupilId
    );

}
