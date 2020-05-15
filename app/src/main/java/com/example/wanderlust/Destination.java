package com.example.wanderlust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Destination extends AppCompatActivity {

    ImageButton banglore,mumbai,delhi,chennai,pune,kolkata,hyderabad,panaji,ahmedabad,jaipur;
    String city_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

         banglore = (ImageButton) findViewById(R.id.banglore);
        banglore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Bangalore";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        mumbai = (ImageButton) findViewById(R.id.mumbai);
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Mumbai";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        delhi = (ImageButton) findViewById(R.id.delhi);
        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Delhi";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        chennai = (ImageButton) findViewById(R.id.chennai);
        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Chennai";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        pune = (ImageButton) findViewById(R.id.pune);
        pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Pune";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        kolkata = (ImageButton) findViewById(R.id.kolkata);
        kolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Kolkata";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        hyderabad = (ImageButton) findViewById(R.id.hydrabad);
        hyderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Hyderabad";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        panaji = (ImageButton) findViewById(R.id.panaji);
        panaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Panaji";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        ahmedabad = (ImageButton) findViewById(R.id.ahmedabad);
        ahmedabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Ahmedabad";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });

        jaipur = (ImageButton) findViewById(R.id.jaipur);
        jaipur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name="Jaipur";
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city",city_name);
                startActivity(intent);
            }
        });


    }
}
