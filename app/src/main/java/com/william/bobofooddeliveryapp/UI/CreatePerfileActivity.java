package com.william.bobofooddeliveryapp.UI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.databinding.ActivityCreatePerfileBinding;
import com.william.bobofooddeliveryapp.databinding.ActivityRegisterBinding;

import java.util.Calendar;

public class CreatePerfileActivity extends AppCompatActivity {

    private ActivityCreatePerfileBinding binding;
    private Context context;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCreatePerfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        view = binding.getRoot();
        context = this;
        //calendario
        TextInputEditText dateOfBirthEditText  = binding.dateOfBirthEditText;
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener  dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = String.format("%d/%d/%d",dayOfMonth,month + 1,year);
                dateOfBirthEditText.setText(selectedDate);
            }
        };
        dateOfBirthEditText.setOnClickListener(view1 -> {
            new DatePickerDialog(
                    context,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

//        dateOfBirthEditText.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(
//                        context,
//                        dateSetListener,
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH)
//                ).show();
//            }
//        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String img = "https://i.pinimg.com/736x/de/fb/5d/defb5d6f33060fcc46dbc889721b2947.jpg";
        ImageView imageView = null;

    }
}