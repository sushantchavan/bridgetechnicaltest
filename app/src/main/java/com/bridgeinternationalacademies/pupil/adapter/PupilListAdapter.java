package com.bridgeinternationalacademies.pupil.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bridgeinternationalacademies.pupil.R;
import com.bridgeinternationalacademies.pupil.model.Pupil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sushantchavan on 17/04/17.
 */

public class PupilListAdapter extends RecyclerView.Adapter<PupilListAdapter.MyViewHolder>  {

    private List<Pupil> listOfPupil;
    private int musterNumber;
    private int countInMuster;
    private int totalMusters;

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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView country;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_view_name);
            country = (TextView) itemView.findViewById(R.id.txt_view_country);
        }
    }

    public  PupilListAdapter(List<Pupil> listOfPupil, int musterNumber, int countInMuster, int totalMusters ) {
        this.listOfPupil = listOfPupil;
        this.musterNumber = musterNumber;
        this.countInMuster = countInMuster;
        this.totalMusters = totalMusters;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pupil_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pupil pupil = listOfPupil.get(position);
        holder.name.setText(pupil.getName());
        holder.country.setText(pupil.getCountry());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
       return listOfPupil.size();
    }


}
