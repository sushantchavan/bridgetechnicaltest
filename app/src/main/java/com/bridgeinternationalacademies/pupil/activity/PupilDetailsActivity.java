package com.bridgeinternationalacademies.pupil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bridgeinternationalacademies.pupil.R;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by sushantchavan on 20/04/17.
 */

public class PupilDetailsActivity extends AppCompatActivity {
    private EditText pd_input_name;
    private Spinner pd_input_country;
    private CircularImageView pd_img_profile_image;
    private EditText pd_input_latitude;
    private EditText pd_input_longitude;
    private Button btn_edit;
    private Button btn_cancel;
    private Pupil pupil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pupil_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent i = getIntent();
        Pupil pupil = (Pupil) i.getSerializableExtra("pupil");

        String[] countryList = getResources().getStringArray(R.array.countryList);

        pd_input_name = (EditText) findViewById(R.id.pd_input_name);
        pd_img_profile_image = (CircularImageView) findViewById(R.id.pd_img_profile_image);
        pd_input_country = (Spinner) findViewById(R.id.pd_spinner_country);
        pd_input_latitude = (EditText) findViewById(R.id.pd_input_latitude);
        pd_input_longitude = (EditText) findViewById(R.id.pd_input_longitude);
        btn_edit = (Button) findViewById(R.id.btn_edit_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        pd_input_name.setEnabled(false);
        pd_input_latitude.setEnabled(false);
        pd_input_longitude.setEnabled(false);
        pd_input_country.setEnabled(false);

        if(pupil != null) {
            setEditMode(false);

            pd_input_name.setText(pupil.getName());

            pd_input_latitude.setText(String.valueOf(pupil.getLatitude()));
            pd_input_longitude.setText(String.valueOf(pupil.getLongitude()));

            Glide.with(this).load(pupil.getImage()).into(pd_img_profile_image);

            pd_input_country.setSelection(Arrays.asList(countryList).indexOf(pupil.getCountry()));
        } else {
            setEditMode(true);
            btn_edit.setText("Save");
            btn_cancel.setEnabled(true);
            btn_cancel.setVisibility(View.VISIBLE);
        }

        //Make input fields uneditabled


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Button b = (Button) v;
               if(b.getText().toString().equals("Edit")) {
                   btn_cancel.setEnabled(true);
                   btn_cancel.setVisibility(View.VISIBLE);
                   b.setText("Save");
                   setEditMode(true);


               } else {
                   setEditMode(false);
               }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if(b.getText().toString().equals("Save")) {
                    btn_edit.setText("Edit");
                    b.setEnabled(false);
                    setEditMode(false);
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

    private void setEditMode (boolean bool) {
        pd_input_name.setEnabled(bool);
        pd_input_latitude.setEnabled(bool);
        pd_input_longitude.setEnabled(bool);
        pd_input_country.setEnabled(bool);

    }
}
