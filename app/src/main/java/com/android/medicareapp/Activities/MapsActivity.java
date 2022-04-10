package com.android.medicareapp.Activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.android.medicareapp.R;
import com.android.medicareapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        AdapterView.OnItemSelectedListener{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    String[] types = { "Pharmacy", "Hospital", "Blood Banks", "Testing Labs"};
    String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchSpinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.searchSpinner.setAdapter(aa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public String getType() {
        return type;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        displayPharmacy();
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    void displayPharmacy() {
//        mMap.clear();
        List<LatLng> coordinates = new ArrayList<>();
        coordinates.add(new LatLng(31.5497, 74.3436));
        coordinates.add(new LatLng(32.595329597, 74.076218605));
        coordinates.add(new LatLng(31.5412041772, 74.3339574337));
        coordinates.add(new LatLng(31.4678924167, 74.2608216405));
        coordinates.add(new LatLng(31.5497, 74.3436));
        for(int i=0;i<coordinates.size();i++)
        mMap.addMarker(new MarkerOptions().position(coordinates.get(i)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(0)));
    }
    void displaylabs() {
        mMap.clear();
        List<LatLng> coordinates = new ArrayList<>();
        coordinates.add(new LatLng(31.4843482109, 74.3005978007));
        coordinates.add(new LatLng(31.7468723189, 73.7993241906));
        coordinates.add(new LatLng(33.6069663692, 73.0145820738));
        coordinates.add(new LatLng(30.1926553119, 71.4460659027));
        coordinates.add(new LatLng(30.202104494, 71.4506626975));
        for(int i=0;i<coordinates.size();i++)
        mMap.addMarker(new MarkerOptions().position(coordinates.get(i)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(0)));
    }
    void displayHospitals() {
        mMap.clear();
        List<LatLng> coordinates = new ArrayList<>();
        coordinates.add(new LatLng(31.4843482109, 74.3005978007));
        coordinates.add(new LatLng(31.7468723189, 73.7993241906));
        coordinates.add(new LatLng(33.6069663692, 73.0145820738));
        coordinates.add(new LatLng(30.1926553119, 71.4460659027));
        coordinates.add(new LatLng(30.202104494, 71.4506626975));
        for(int i=0;i<coordinates.size();i++)
        mMap.addMarker(new MarkerOptions().position(coordinates.get(i)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(0)));
    }
    void displayBloodbanks() {
        mMap.clear();
        List<LatLng> coordinates = new ArrayList<>();
        coordinates.add(new LatLng(31.4843482109, 74.3005978007));
        coordinates.add(new LatLng(31.7468723189, 73.7993241906));
        coordinates.add(new LatLng(33.6069663692, 73.0145820738));
        coordinates.add(new LatLng(30.1926553119, 71.4460659027));
        coordinates.add(new LatLng(30.202104494, 71.4506626975));
        for(int i=0;i<coordinates.size();i++)
        mMap.addMarker(new MarkerOptions().position(coordinates.get(i)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(0)));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = types[position];
        switch (position)
        {
            case 0: displayPharmacy(); break;
            case 1: displayHospitals(); break;
            case 2: displayBloodbanks(); break;
            case 3: displaylabs(); break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}