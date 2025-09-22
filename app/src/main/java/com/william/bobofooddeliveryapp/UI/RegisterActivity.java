package com.william.bobofooddeliveryapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private View view;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        view = binding.getRoot();
        context = this;

        binding.btnBackRegister.setOnClickListener(view1 -> login());
        binding.btnRegister.setOnClickListener(view1 -> createPerfile());

    }

    private void createPerfile() {
        Intent intent = new Intent(RegisterActivity.this, CreatePerfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void login(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}