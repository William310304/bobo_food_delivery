package com.william.bobofooddeliveryapp.UI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.databinding.ActivityCreatePerfileBinding;
import com.william.bobofooddeliveryapp.databinding.ActivityRegisterBinding;

import java.util.Calendar;

public class CreatePerfileActivity extends AppCompatActivity {

    private ActivityCreatePerfileBinding binding;
    private Context context;
    private View view;
    private FloatingActionButton selectPhoto;
    private ImageView profiImageView;

    // Variables para guardar la dirección recibida
    private String addressLabel = "";
    private String instructions = "";
    private String street = "";
    private String city = "";
    private String province = "";
    private String codePostal = "";
    private String county = "";

    private final ActivityResultLauncher<Intent> addressLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    addressLabel = data.getStringExtra("addressLabel");
                    instructions = data.getStringExtra("instructions");
                    street = data.getStringExtra("street");
                    city = data.getStringExtra("city");
                    province = data.getStringExtra("province");
                    codePostal = data.getStringExtra("codePostal");
                    county = data.getStringExtra("county");

                    binding.addressTextEdit.setText(addressLabel + " - " + city);
                }
            }
    );

    private final ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    profiImageView.setImageURI(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCreatePerfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        view = binding.getRoot();
        context = this;
        //calendario ==================================================================
        TextInputEditText dateOfBirthEditText = binding.dateOfBirthEditText;
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
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
        //=========================================================================

        //add photo profile ==============================
        profiImageView = binding.profileImage;
        selectPhoto = binding.changePhotoButton;
        selectPhoto.setOnClickListener(view1 -> {
            getContent.launch("image/*");
        });
        //===========================================================

        //ir a la otra activity ================
        binding.addressTextEdit.setOnClickListener(view1 -> {

            Intent intent = new Intent(context, AddNewAddressActivity.class);
            intent.putExtra("fullName", binding.fullName.getEditText().getText().toString());
            intent.putExtra("phone", binding.phoneNumber.getEditText().getText().toString());
            intent.putExtra("dateOfBirth", binding.dateOfBirthEditText.getText().toString());
            // Y además pasar lo que ya habías guardado en dirección
            intent.putExtra("addressLabel", addressLabel);
            intent.putExtra("instructions", instructions);
            intent.putExtra("street", street);
            intent.putExtra("city", city);
            intent.putExtra("province", province);
            intent.putExtra("codePostal", codePostal);
            intent.putExtra("county", county);
            addressLauncher.launch(intent);

        });
        //=================

        binding.bntContinue.setOnClickListener(view1 -> {

        });



    }


}