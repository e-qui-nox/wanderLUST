package com.example.wanderlust;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class WorldClockActivity extends AppCompatActivity {

    Calendar current;
    Spinner spinner;
    TextView timezone, txtCurrentTime,txtTimeZoneTime;
    long milliseconds;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat sdf;
    Date resultDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#018db7"));
        actionBar.setBackgroundDrawable(colorDrawable);

        spinner = (Spinner)findViewById(R.id.spinner);
        timezone = (TextView)findViewById(R.id.timezone);
        txtCurrentTime = (TextView)findViewById(R.id.txtCurrentTime);
        txtTimeZoneTime = (TextView)findViewById(R.id.txtTimeZoneTime);

        String[] idArray = TimeZone.getAvailableIDs();
        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");

        idAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,idArray);

        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);

        getGMTtime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));
                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();

                int TimeZoneOffset = timeZone.getRawOffset() / (60 * 1000);
                int hrs = TimeZoneOffset / 60;
                int mins = TimeZoneOffset % 60;

                milliseconds = milliseconds + timeZone.getRawOffset();
                resultDate = new Date(milliseconds);
                System.out.println(sdf.format(resultDate));

                timezone.setText(TimeZoneName + " : GMT" + hrs + ":" + mins);
                txtTimeZoneTime.setText("" + sdf.format(resultDate));
                milliseconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void getGMTtime(){
        current = Calendar.getInstance();
        txtCurrentTime.setText("" + current.getTime());

        milliseconds = current.getTimeInMillis();

        TimeZone txtCurrent = current.getTimeZone();
        int offset = txtCurrent.getRawOffset();

        if(txtCurrent.inDaylightTime(new Date())){
            offset = offset + txtCurrent.getDSTSavings();
        }

        milliseconds = milliseconds - offset;
        resultDate = new Date(milliseconds);
        System.out.println(sdf.format(resultDate));

    }
}

