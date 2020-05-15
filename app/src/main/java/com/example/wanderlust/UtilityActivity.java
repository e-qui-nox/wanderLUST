package com.example.wanderlust;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UtilityActivity extends AppCompatActivity {

    ImageButton checklist;
    ImageButton compass;
    ImageButton weather;
    ImageButton world_clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#018db7"));
        actionBar.setBackgroundDrawable(colorDrawable);

        checklist = (ImageButton)findViewById(R.id.checklist);
        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChecklistActivity.class);
                startActivity(intent);
            }
        });

        compass = (ImageButton)findViewById(R.id.compass);
        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CompassActivity.class);
                startActivity(intent);
            }
        });

        weather = (ImageButton)findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WeatherActivity.class);
                startActivity(intent);
            }
        });

        world_clock = (ImageButton)findViewById(R.id.clock);
        world_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WorldClockActivity.class);
                startActivity(intent);
            }
        });


    }
}

