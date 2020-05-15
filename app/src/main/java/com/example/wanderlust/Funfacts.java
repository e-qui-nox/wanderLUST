package com.example.wanderlust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Funfacts extends AppCompatActivity {

    public static TextView fetchdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funfacts);

        Intent intent = getIntent();
        String city=intent.getStringExtra("city");
        Log.d("TAG", "onCreate: "+city);
        fetchdata=(TextView) findViewById(R.id.funfactstv);
        fetchwiki  fetchprocess= new fetchwiki(city);
        fetchprocess.execute();

    }
}
