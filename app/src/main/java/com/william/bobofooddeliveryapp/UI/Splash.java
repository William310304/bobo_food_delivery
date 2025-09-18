package com.william.bobofooddeliveryapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.william.bobofooddeliveryapp.MainActivity;
import com.william.bobofooddeliveryapp.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(()->{
            Intent intent = new Intent(Splash.this, Onboarding.class);
            startActivity(intent);
            finish(); // Cierra SplashActivity para que no vuelva atrás
        },3200);
    }
}