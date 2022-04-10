package com.android.medicareapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.android.medicareapp.R;
import com.android.medicareapp.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivitySearchBinding binding;
    String[] types = { "Pharmacy", "Hospital", "Blood Banks", "Testing Labs"};
    String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchSpinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.searchSpinner.setAdapter(aa);
    }

    public String getType() {
        return type;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = types[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}