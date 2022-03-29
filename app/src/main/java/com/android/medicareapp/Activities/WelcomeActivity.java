package com.android.medicareapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.android.medicareapp.R;
import com.android.medicareapp.Utils.Constants;
import com.android.medicareapp.databinding.ActivityMainBinding;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.registerBtn.setOnClickListener(this); // attached  button listerners
        binding.customerBtn.setOnClickListener(this);
        binding.businessBtn.setOnClickListener(this);
        binding.loginBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registerBtn:
                new Handler().postDelayed(new Runnable() { // delay button effect
                    @Override
                    public void run() {
                        binding.welcomePage1.setVisibility(View.GONE);
                        binding.welcomePage2.setVisibility(View.VISIBLE);
                    }
                },500);
                break;
            case R.id.customerBtn:
                startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class)
                        .putExtra(Constants.as,Constants.customer));
                break;
            case R.id.businessBtn:
                startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class)
                        .putExtra(Constants.as,Constants.business));
                break;
            case R.id.loginBtn:
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(binding.welcomePage2.getVisibility()==View.VISIBLE){
            binding.welcomePage1.setVisibility(View.VISIBLE);
            binding.welcomePage2.setVisibility(View.GONE);
        }
        else super.onBackPressed();
    }
}