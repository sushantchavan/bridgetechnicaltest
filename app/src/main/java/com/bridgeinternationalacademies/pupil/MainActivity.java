package com.bridgeinternationalacademies.pupil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bridgeinternationalacademies.pupil.activity.PupilDetailsActivity;
import com.bridgeinternationalacademies.pupil.adapter.PupilListAdapter;
import com.bridgeinternationalacademies.pupil.callback.GenericCallback;
import com.bridgeinternationalacademies.pupil.database.DatabaseHelper;
import com.bridgeinternationalacademies.pupil.eventlisteners.recycleViewerPupilsTouchListener;
import com.bridgeinternationalacademies.pupil.manager.PupilManager;
import com.bridgeinternationalacademies.pupil.model.Classroom;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.bridgeinternationalacademies.pupil.network.ServiceManager;
import com.bridgeinternationalacademies.pupil.util.NetworkHelper;
import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private List<Pupil> listOfPupil = new ArrayList<>();
    private RecyclerView recyclerViewPupils;
    private PupilListAdapter mPupilAdapter;
    public PupilManager mPupilManager = new PupilManager();
    private DatabaseHelper db = new DatabaseHelper(this);

    private int musterNumber = 1;
    private int countInMuster = 0 ;
    private int totalMusters = 0;
    private ProgressBar progressBar;

    NetworkHelper mNetworkHelper = new NetworkHelper(this);


    public  interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewPupils = (RecyclerView) findViewById(R.id.recyclerViewPupils);
        recyclerViewPupils.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.GRAY)
                        .build());


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
                Intent intent = new Intent(MainActivity.this, PupilDetailsActivity.class);
                startActivity(intent);

            }
        });



        if(mNetworkHelper.isNetworkAvailable()) {
            mPupilManager.getListOfPupils(musterNumber, new GenericCallback<Classroom>() {
                @Override
                public void onRequestSuccess(Classroom objectToReturn) {
                    if(objectToReturn != null) {

                        listOfPupil = objectToReturn.getPupil();
                        musterNumber = objectToReturn.getMusterNumber();
                        countInMuster = objectToReturn.getCountInMuster();
                        totalMusters = objectToReturn.getTotalMusters();
                        mPupilAdapter = new PupilListAdapter(recyclerViewPupils.getContext(), listOfPupil, musterNumber, countInMuster, totalMusters);
                        recyclerViewPupils.setAdapter(mPupilAdapter);
                        mPupilAdapter.notifyDataSetChanged();
                        updatePupilTable(objectToReturn);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.service_down_error, Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onRequestFailure(Throwable error, String errorMessage) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, R.string.internet_error_connectivity, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, R.string.internet_error_fetch_local_data, Toast.LENGTH_SHORT).show();
            List<Pupil> mListOfPupilfromdB = db.getAllPupil();
            if(mListOfPupilfromdB != null && mListOfPupilfromdB.size() > 0) {
                mPupilAdapter = new PupilListAdapter(recyclerViewPupils.getContext(), mListOfPupilfromdB, musterNumber, countInMuster, totalMusters);
                recyclerViewPupils.setAdapter(mPupilAdapter);
                mPupilAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            } else {
                Toast.makeText(MainActivity.this, R.string.error_no_local_data_found, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
            progressBar.setVisibility(View.INVISIBLE);

        }

        recyclerViewPupils.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(musterNumber != totalMusters) {
                    musterNumber = musterNumber+1;
                    mPupilManager.getListOfPupils(musterNumber, new GenericCallback<Classroom>() {
                        @Override
                        public void onRequestSuccess(Classroom objectToReturn) {

                            if(objectToReturn != null) {
                                for (int i=0; i<objectToReturn.getPupil().size(); i++) {
                                    if(!checkObject(listOfPupil, objectToReturn.getPupil().get(i).getPupilId())) {
                                        listOfPupil.add(objectToReturn.getPupil().get(i));
                                    }
                                }
                                mPupilAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onRequestFailure(Throwable error, String errorMessage) {
                            Toast.makeText(MainActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        recyclerViewPupils.addOnItemTouchListener(new recycleViewerPupilsTouchListener(getApplicationContext(), recyclerViewPupils, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Pupil nextPupil = listOfPupil.get(position);
                Intent intent = new Intent(MainActivity.this, PupilDetailsActivity.class);
                intent.putExtra("pupil", nextPupil);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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

    private void updatePupilTable(Classroom classroom) {

        List<Pupil> Pupils = classroom.getPupil() ;
        for (Pupil p: Pupils) {
            if(db.getPupil(p.getPupilId()) == null) {
                db.insertPupil(p);
            }
        }
    }

    private boolean checkObject(List<Pupil> listOfPupil, int pupilId) {
        boolean status = false;
        for(Pupil p: listOfPupil) {
            if(p.getPupilId() == pupilId) {
                status = true;
            }
        }
        return status;
    }

}
