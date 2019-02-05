package com.example.geoquiz;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MapQuestion extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    MapView mapView;
    GoogleMap gmap;
    TextView question_situer;
    TextView distance;
    TextView randomCountry;
    TextView theDistance;
    LatLng countryCapitale;
    LatLng userTouche;
    double distanceCalcule;
    int i = 0;
    String theDestination = "";
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_question);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        findByView();
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        dbHelper = new DataBaseHelper(this);


    }

    String randomCountry() {
        Random rnd = new Random();
        return dbHelper.getAll().get(rnd.nextInt(dbHelper.getAll().size())).getPays();
    }

    String randomCapital() {
        Random rnd = new Random();
        return dbHelper.getAll().get(rnd.nextInt(dbHelper.getAll().size())).getCapitale();
    }

    public void findByView() {
        mapView = findViewById(R.id.mapView);
        question_situer = findViewById(R.id.tv_situer);
        distance = findViewById(R.id.tv_distance);
        randomCountry = findViewById(R.id.country_tv);
        theDistance = findViewById(R.id.tv_Thedistance);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        //gmap.setMinZoomPreference(5);
        gmap.setIndoorEnabled(true);

        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        LatLng ny = new LatLng(40.7143528, -74.0059731);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gmap.addMarker(markerOptions);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));


        getTouchCordinate();
    }

    public void goToLocationByName() {
        List<Address> addressList = null;
        String location = randomCountry.getText().toString();
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            countryCapitale = new LatLng(address.getLatitude(), address.getLongitude());

            gmap.addMarker(new MarkerOptions().position(latLng).title(location));
            gmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            //Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
        }
    }

    public void onValide(View view) {
        goToLocationByName();
        drawDistance();
        drawMarker(countryCapitale);
        drawMarker(userTouche);
        distanceCalcule = getDistanceInKilo(userTouche.latitude, userTouche.longitude, countryCapitale.latitude, countryCapitale.longitude);
        theDistance.setText("  " + getDistanceInKilo(userTouche.latitude, userTouche.longitude, countryCapitale.latitude, countryCapitale.longitude));
        toastMessage();
    }

    public void onNew(View view) {
        gmap.clear();
        theDistance.setText("");
        randomSetText();
    }

    public void onChangeType(View view) {

        int[] mapType = {GoogleMap.MAP_TYPE_HYBRID, GoogleMap.MAP_TYPE_NORMAL, GoogleMap.MAP_TYPE_SATELLITE, GoogleMap.MAP_TYPE_TERRAIN};
        if (i < mapType.length) {
            gmap.setMapType(mapType[i]);
            i++;
        } else {
            i = 0;
        }
    }

    public void getTouchCordinate() {
        gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();
               // LatLng countryCapitale;
                //LatLng userTouche;
                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                userTouche = new LatLng(latLng.latitude, latLng.longitude);

                // Clears the previously touched position
                gmap.clear();
                //égoToLocationByName();

                // Animating to the touched position
                //gmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                //gmap.addMarker(markerOptions);
            }

        });
    }

    public void randomSetText() {
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;

        if (randomInt % 2 == 0) {

            randomCountry.setText(" "+randomCapital());
        } else {
            randomCountry.setText(" "+randomCountry());
        }

        theDestination = randomCountry.getText().toString();
    }

    public void drawDistance() {
        PolylineOptions poly = new PolylineOptions();
        poly.add(countryCapitale);
        poly.add(userTouche);
        poly.color(Color.RED);
        poly.geodesic(true);
        poly.startCap(new RoundCap());
        poly.width(20);
        poly.jointType(JointType.BEVEL);

        gmap.addPolyline(poly);


    }


    public double getDistanceInKilo(double startLat, double startLong, double endLat, double endLong) {
        Location sourceLoc = new Location("");
        sourceLoc.setLatitude(startLat);
        sourceLoc.setLongitude(startLong);
        Location destLoc = new Location("");
        destLoc.setLatitude(endLat);
        destLoc.setLongitude(endLong);
        return sourceLoc.distanceTo(destLoc) * 0.001;
    }

    private void drawMarker(LatLng point) {
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Adding marker on the Google Map
        gmap.addMarker(markerOptions);
    }

    private void toastMessage() {
        if (distanceCalcule <= 100) {
            Toast.makeText(this, "Bravo vous etes pas loin de " + theDestination, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vous etes trop loin de " + theDistance, Toast.LENGTH_SHORT).show();

        }
    }

}
