package com.bridgeinternationalacademies.pupil;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.bridgeinternationalacademies.pupil.adapter.PupilListAdapter;
import com.bridgeinternationalacademies.pupil.callback.GenericCallback;
import com.bridgeinternationalacademies.pupil.manager.PupilManager;
import com.bridgeinternationalacademies.pupil.model.Classroom;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.bridgeinternationalacademies.pupil.network.ServiceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Pupil> listOfPupil = new ArrayList<>();
    private RecyclerView recyclerViewPupils;
    private PupilListAdapter mPupilAdapter;
    public PupilManager mPupilManager = new PupilManager();
    private int musterNumber = 1;
    private int countInMuster;
    private int totalMusters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewPupils = (RecyclerView) findViewById(R.id.recyclerViewPupils);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPupils.setLayoutManager(mLayoutManager);
        recyclerViewPupils.setItemAnimator(new DefaultItemAnimator());

        recyclerViewPupils.setAdapter(mPupilAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*

        getListOfPupils(new GenericCallback<Classroom>() {
            @Override
            public void onRequestSuccess(Classroom objectToReturn) {
                listOfPupil = objectToReturn.getPupil();
                mPupilAdapter = new PupilListAdapter(listOfPupil);
                recyclerViewPupils.setAdapter(mPupilAdapter);
                mPupilAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRequestFailure(Throwable error, String errorMessage) {

            }
        });*/

        mPupilManager.getListOfPupils(musterNumber, new GenericCallback<Classroom>() {
            @Override
            public void onRequestSuccess(Classroom objectToReturn) {
                listOfPupil = objectToReturn.getPupil();
                musterNumber = objectToReturn.getMusterNumber();
                countInMuster = objectToReturn.getCountInMuster();
                totalMusters = objectToReturn.getTotalMusters();
                mPupilAdapter = new PupilListAdapter(listOfPupil, musterNumber, countInMuster, totalMusters);
                recyclerViewPupils.setAdapter(mPupilAdapter);
                mPupilAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRequestFailure(Throwable error, String errorMessage) {

            }
        });

        recyclerViewPupils.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(musterNumber != totalMusters) {
                    musterNumber = mPupilAdapter.getMusterNumber() + 1;
                    mPupilManager.getListOfPupils(musterNumber, new GenericCallback<Classroom>() {
                        @Override
                        public void onRequestSuccess(Classroom objectToReturn) {

                            if(objectToReturn != null) {
                                for (int i=0; i<objectToReturn.getPupil().size(); i++) {
                                    listOfPupil.add(objectToReturn.getPupil().get(i));
                                }
                                countInMuster = objectToReturn.getCountInMuster();
                                musterNumber = objectToReturn.getMusterNumber();
                                totalMusters = objectToReturn.getTotalMusters();

                                mPupilAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onRequestFailure(Throwable error, String errorMessage) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
