package com.android.medicareapp.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.medicareapp.Adapters.homeadapter.CustomerHomeAdapter;
import com.android.medicareapp.Adapters.homeadapter.CustomerHomeModel;
import com.android.medicareapp.R;
import com.android.medicareapp.Utils.Constants;
import com.android.medicareapp.databinding.ActivityCustomerhomeBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityCustomerhomeBinding binding;
    ActionBarDrawerToggle actionBarDrawerToggle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerhomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<CustomerHomeModel> list = new ArrayList<>();
        list.add(new CustomerHomeModel("Pharmacy"));
        list.add(new CustomerHomeModel("Hospital"));
        list.add(new CustomerHomeModel("Blood\nBanks"));
        list.add(new CustomerHomeModel("Testing\nLabs"));
        binding.rvhome.setAdapter(new CustomerHomeAdapter(CustomerHomeActivity.this, list));

        binding.msgBtn.setOnClickListener(this);
        binding.orderBtn.setOnClickListener(this);
        binding.bookingBtn.setOnClickListener(this);
        binding.menuBtn.setOnClickListener(this);
        binding.field.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.msgBtn:
                startActivity(new Intent(CustomerHomeActivity.this, CustomerOperations.class)
                .putExtra(Constants.operation, Constants.msg));
                break;
            case R.id.bookingBtn:
                startActivity(new Intent(CustomerHomeActivity.this, CustomerOperations.class)
                        .putExtra(Constants.operation, Constants.booking));
                break;
            case R.id.orderBtn:
                startActivity(new Intent(CustomerHomeActivity.this, CustomerOperations.class)
                        .putExtra(Constants.operation, Constants.order));
                break;
            case R.id.menuBtn:
                binding.drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.field:
                startActivity(new Intent(CustomerHomeActivity.this, MapsActivity.class));
                break;
        }
    }
}