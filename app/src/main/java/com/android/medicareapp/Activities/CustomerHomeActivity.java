package com.android.medicareapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.medicareapp.Adapters.chat.CustomerHomeAdapter;
import com.android.medicareapp.Adapters.chat.CustomerHomeModel;
import com.android.medicareapp.R;
import com.android.medicareapp.databinding.ActivityCustomerhomeBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeActivity extends AppCompatActivity {
    ActivityCustomerhomeBinding binding;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}