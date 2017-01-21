package com.example.ciuc.shoppingassistant;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class Map extends Activity implements OnMapReadyCallback,LocationListener {



    public static final int ACCESS_FINE_REQUEST_CODE = 10;
    public static final int ACCESS_COARSE_REQUEST_CODE = 11;

    public boolean googleServicesAviable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvaible = api.isGooglePlayServicesAvailable(this);
        if (isAvaible == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvaible)) {
            Dialog dialog = api.getErrorDialog(this, isAvaible, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;

    }

    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    MapView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (googleServicesAviable()) {
            //Toast.makeText(this,"Loading map...",Toast.LENGTH_LONG).show();
            setContentView(R.layout.map);

        } else {
            //No Google Maps Layout
        }
        backToMain();
        loadMap(savedInstanceState);

    }

    private void backToMain() {
        Button backButton = (Button) findViewById(R.id.backFromMapToMain);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showMain = new Intent(Map.this, MainActivity.class);
                startActivity(showMain);

            }
        });
    }

    private void loadMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.getMapAsync(this);
        mMapView.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) ) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_REQUEST_CODE);
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_REQUEST_CODE);

            }
        }

        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case ACCESS_COARSE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




    private void goToLocation(double lat, double lng) {
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLng(ll);
        mMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mMap.moveCamera(update);
    }


    @Override
    public void onLocationChanged(Location location) {
        if(location==null){
            Toast.makeText(this,"Cant get curent location",Toast.LENGTH_LONG).show();
        }else{
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            goToLocationZoom(ll.latitude,ll.longitude,18);

        }
    }
}
