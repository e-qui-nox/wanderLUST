package com.example.wanderlust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        WebView mywebview = (WebView) findViewById(R.id.webview);
        mywebview.loadUrl("https://www.booking.com/");
        finish();

    }
}
