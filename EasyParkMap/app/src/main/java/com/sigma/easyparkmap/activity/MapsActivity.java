package com.sigma.easyparkmap.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.sigma.easyparkmap.R;
import com.sigma.easyparkmap.helper.LocationHelper;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> listLatLng = new ArrayList<LatLng>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        List<String> listString = new LocationHelper().getArraylist(intent.getStringExtra("city"));


        for (String element : listString) {
            String[] splitStr = element.split("\\s+");
            listLatLng.add(new LatLng(Double.parseDouble(splitStr[1]), Double.parseDouble(splitStr[0])));
        }


        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        PolygonOptions opts = new PolygonOptions();
        for (LatLng location : listLatLng) {
            opts.add(location);
        }

        Polygon polygon = mMap.addPolygon(opts.strokeColor(Color.RED).fillColor(0x7F00FF00));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(listLatLng.get(0).latitude, listLatLng.get(0).longitude), 15));

    }
}