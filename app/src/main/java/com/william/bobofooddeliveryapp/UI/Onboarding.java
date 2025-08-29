package com.william.bobofooddeliveryapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.adapter.OnboardingAdapter;

import java.util.Arrays;
import java.util.List;

public class Onboarding extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnNext;
    private Button btnSkip;
    private OnboardingAdapter adapter;
    private List<Integer> layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);

        layouts = Arrays.asList(
                R.layout.item_onboarding1,
                R.layout.item_onboarding2,
                R.layout.item_onboarding3,
                R.layout.item_onboarding4
        );

        adapter = new OnboardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);

        btnSkip.setOnClickListener(v -> {
            goToLogin();
        });

        btnNext.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();

            if (current < layouts.size() - 1) {

                viewPager.setCurrentItem(current + 1);
            } else {
                goToLogin();
            }
        });

        // Cambiar texto de "Next" en la última página
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                if (position == layouts.size() - 1) {
//                    btnNext.setText("Empezar");
//                    btnSkip.setVisibility(Button.GONE); // opcional ocultar skip
//                } else {
//                    btnNext.setText("Next");
//                    btnSkip.setVisibility(Button.VISIBLE);
//                }
//            }
//        });

    }
    private void goToLogin() {
        Intent intent = new Intent(Onboarding.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}