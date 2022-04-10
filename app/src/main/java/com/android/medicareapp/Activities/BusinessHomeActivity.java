package com.android.medicareapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.medicareapp.R;
import com.android.medicareapp.Utils.Constants;
import com.android.medicareapp.databinding.ActivityBusinessHomeBinding;

public class BusinessHomeActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityBusinessHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.first.text.setText("Manage\nBooking\nRequests");
        binding.second.text.setText("Manage\nOrder\nRequests");
        binding.first.getRoot().setOnClickListener(this);
        binding.second.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.first:
                startActivity(new Intent(BusinessHomeActivity.this, CustomerOperations.class)
                        .putExtra(Constants.operation, Constants.bookingrequest));
                break;
            case R.id.second:
                startActivity(new Intent(BusinessHomeActivity.this, CustomerOperations.class)
                        .putExtra(Constants.operation, Constants.orderrequest));
                break;
        }
    }
}