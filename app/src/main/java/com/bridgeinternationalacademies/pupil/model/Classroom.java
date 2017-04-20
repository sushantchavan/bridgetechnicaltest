package com.bridgeinternationalacademies.pupil.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sushantchavan on 19/04/17.
 */

public class Classroom {
    @SerializedName("items")
    private List<Pupil> pupil;

    @SerializedName("pageNumber")
    private int musterNumber;

    @SerializedName("itemCount")
    private int countInMuster;

    @SerializedName("totalPages")
    private int totalMusters;



    public List<Pupil> getPupil() {
        return pupil;
    }

    public void setPupil(List<Pupil> pupil) {
        this.pupil = pupil;
    }

    public int getMusterNumber() {
        return musterNumber;
    }

    public void setMusterNumber(int musterNumber) {
        this.musterNumber = musterNumber;
    }

    public int getCountInMuster() {
        return countInMuster;
    }

    public void setCountInMuster(int countInMuster) {
        this.countInMuster = countInMuster;
    }

    public int getTotalMusters() {
        return totalMusters;
    }

    public void setTotalMusters(int totalMusters) {
        this.totalMusters = totalMusters;
    }
}
