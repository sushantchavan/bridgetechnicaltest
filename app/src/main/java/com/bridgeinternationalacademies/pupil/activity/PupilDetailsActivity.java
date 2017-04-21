package com.bridgeinternationalacademies.pupil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.bridgeinternationalacademies.pupil.R;
import com.bridgeinternationalacademies.pupil.model.Pupil;

/**
 * Created by sushantchavan on 20/04/17.
 */

public class PupilDetailsActivity extends AppCompatActivity {
    public TextView pd_input_name;
    public Spinner pd_input_country;
    public TextView pd_input_latitude;
    public Pupil pupil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pupil_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Pupil pupil = (Pupil) i.getSerializableExtra("pupil");
        pd_input_latitude = (TextView) findViewById(R.id.pd_input_latitude);
        pd_input_name = (TextView) findViewById(R.id.pd_input_name);
        pd_input_country = (Spinner) findViewById(R.id.pd_spinner_country);

        pd_input_name.setText(pupil.getName());


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
