package com.example.wanderlust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    CardView funfacts;
    CardView hangouts;
    CardView destinations;
    CardView shopping;
    CardView restaurant;
    CardView citymap;
    CardView weather;
    ImageSlider is;
    String city;
    public LatLng location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Intent intent = getIntent();
        city=intent.getStringExtra("city");

        TextView imagetext = (TextView) findViewById(R.id.imagetext);
        imagetext.setText(city);


        //Funfacts
        funfacts=findViewById(R.id.fetchdata);
        funfacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this, Funfacts.class);
                intent.putExtra("city",city);
                startActivity(intent);            }
        });


        //Set Slides and LatLng

        switch(city){
            case "Bangalore":
                slides(R.drawable.blr_slide_1,R.drawable.blr_slide_2,R.drawable.blr_slides_3);
                location = new LatLng(12.9716, 77.5946);
                break;
            case "Mumbai":
                slides(R.drawable.mumbai_slide_1,R.drawable.mumbai_slide_2,R.drawable.mumbai_slide_3);
                location = new LatLng(19.0760,72.8777);
                break;
            case "Delhi":
                slides(R.drawable.delhi_slide_2,R.drawable.delhi_slide_1,R.drawable.delhi_slide_3);
                location = new LatLng(28.7041,77.1025);
                break;
            case "Chennai":
                slides(R.drawable.chennai_slide_1,R.drawable.chennai_slide_2,R.drawable.chennai_slide_3);
                location = new LatLng(13.0827,80.2707);
                break;
            case "Pune":
                slides(R.drawable.pune_slides_1,R.drawable.pune_slides_2,R.drawable.pune_slides_3);
                location = new LatLng(18.5204,73.8567);
                break;
            case "Kolkata":
                slides(R.drawable.kolkata_slides_1,R.drawable.kolkata_slides_2,R.drawable.kolkata_slides_3);
                location = new LatLng(22.5726,88.3639);
                break;
            case "Hyderabad":
                slides(R.drawable.hyderabad_slides_1,R.drawable.hyderabad_slides_2,R.drawable.hyderabad_slides_3);
                location = new LatLng(17.3850,78.4867);
                break;
            case "Panaji":
                slides(R.drawable.panaji_slides_1,R.drawable.panaji_slides_2,R.drawable.panaji_slides_3);
                location = new LatLng(15.4909,73.8278);
                break;
            case "Ahmedabad":
                slides(R.drawable.ahmedabad_slides_1,R.drawable.ahmedabad_slides_2,R.drawable.ahmedabad_slides_3);
                location = new LatLng(23.0225,72.5714);
                break;
            case "Jaipur":
                slides(R.drawable.jaipur_slides_1,R.drawable.jaipur_slides_2,R.drawable.jaipur_slides_3);
                location = new LatLng(26.9124,75.7873);
                break;


        }


        // Hangouts

        hangouts=findViewById(R.id.hangout);
        hangouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="cafe|park";
                Bundle args = new Bundle();
                args.putString("type",type);
                args.putParcelable("location", location);
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                intent.putExtra("bundle",args);
                startActivity(intent);
            }
        });

        //Destination
        destinations = (CardView) findViewById(R.id.destinations);
        destinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="tourist_attraction";
                Bundle args = new Bundle();
                args.putString("type",type);
                args.putParcelable("location", location);
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                intent.putExtra("bundle",args);
                startActivity(intent);

            }
        });

        //shopping
        shopping = (CardView) findViewById(R.id.shopping);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="shopping_mall";
                Bundle args = new Bundle();
                args.putString("type",type);
                args.putParcelable("location", location);
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                intent.putExtra("bundle",args);
                startActivity(intent);
            }
        });

        //Restaurant
        restaurant = (CardView) findViewById(R.id.restaurant);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="restaurant";
                Bundle args = new Bundle();
                args.putString("type",type);
                args.putParcelable("location", location);
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                intent.putExtra("bundle",args);
                startActivity(intent);
            }
        });

        //CityMap
        citymap=findViewById(R.id.citymap);
        citymap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putParcelable("location", location);
                Intent intent = new Intent(getApplicationContext(),CityMap.class);
                intent.putExtra("bundle",args);
                startActivity(intent);

            }
        });

        weather = findViewById(R.id.check_weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WeatherActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });


    }




    private void slides(int img_1,int img_2,int img_3) {
        is= findViewById(R.id.slides);
        List<SlideModel> sm = new ArrayList<>();
        sm.add(new SlideModel(img_1));
        sm.add(new SlideModel(img_2));
        sm.add(new SlideModel(img_3));
        is.setImageList(sm,true);
    }
}

















