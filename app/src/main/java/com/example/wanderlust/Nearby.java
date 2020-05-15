package com.example.wanderlust;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Nearby extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private GoogleMap mMap;
    public LatLng place;

    private final float DEFAULT_ZOOM = 15;
    private GoogleApiClient mgoogleApiClient;
    double currentLattitude,currentLongitude;
    Location mylocation;
    public String type;


    private final  static int REQUEST_CHECK_SETTINGS_GPS=0x1;
    private final static  int REQUEST_ID_MULTIPLE_PERMISSION=0x2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getParcelableExtra("bundles");
        this.place = bundle.getParcelable("location");
        type = bundle.getString("type");




        setClient();
    }

    private void setClient() {
        mgoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,0,this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mgoogleApiClient.connect();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.addMarker(new MarkerOptions().position(place).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,DEFAULT_ZOOM ));



    }

    private void getNearBy(String type) {
        StringBuilder stringBuilder =
                new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location="+String.valueOf(place.latitude)+","+String.valueOf(place.longitude));
        stringBuilder.append("&radius=3000");
        stringBuilder.append("&type="+type);
        stringBuilder.append("&key="+getResources().getString(R.string.google_maps_key));

        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[2];
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;

         GetNearByPlacesData getNearByPlacesData = new GetNearByPlacesData();
        getNearByPlacesData.execute(dataTransfer);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermission();

    }

    private void checkPermission() {
        int permissionLocation = ContextCompat.checkSelfPermission(Nearby.this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listpermission = new ArrayList<>();
        if(permissionLocation != PackageManager.PERMISSION_GRANTED){
            listpermission.add( Manifest.permission.ACCESS_FINE_LOCATION);

            if(!listpermission.isEmpty()) {
                ActivityCompat.requestPermissions(this,listpermission.toArray(new String[listpermission.size()]),REQUEST_ID_MULTIPLE_PERMISSION);

            }
        }
        else {
            getMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionLocation == PackageManager.PERMISSION_GRANTED){
             getMyLocation();

        } else {
            checkPermission();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        getNearBy(type);

    }

    private void getMyLocation(){

        if(mgoogleApiClient!=null) {
            if (mgoogleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(Nearby.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(mgoogleApiClient);
                    LocationRequest locationRequest = new LocationRequest();

                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(mgoogleApiClient, locationRequest, (LocationListener) this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(mgoogleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:

                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(Nearby.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {


                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(mgoogleApiClient);


                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {

                                        status.startResolutionForResult(Nearby.this,
                                                REQUEST_CHECK_SETTINGS_GPS);


                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }


                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                                    break;
                            }
                        }
                    });

                }
            }
        }
    }


}
