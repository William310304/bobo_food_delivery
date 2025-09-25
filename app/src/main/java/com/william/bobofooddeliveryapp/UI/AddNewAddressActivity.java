package com.william.bobofooddeliveryapp.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.databinding.ActivityAddNewAddressBinding;

public class AddNewAddressActivity extends AppCompatActivity {
    private ActivityAddNewAddressBinding binding;
    private Context context;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddNewAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        view = binding.getRoot();
        context = this;

        //================= variables ==================
        EditText addressET = binding.textEditAddress.getEditText();
        EditText codePostal = binding.zipCode.getEditText();
        Intent intent = getIntent();
        //======================

        binding.btnBack.setOnClickListener(view1 -> saveNewData());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                boolean allFieldsFiled = !addressET.getText().toString().trim().isEmpty()
                        && !codePostal.getText().toString().isEmpty();
                binding.bntContinue.setEnabled(allFieldsFiled);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        };

        addressET.addTextChangedListener(textWatcher);
        codePostal.addTextChangedListener(textWatcher);

        // Rellenar los campos si ya había datos
        binding.textEditAddress.getEditText().setText(intent.getStringExtra("addressLabel"));
        binding.textEditDeliveryInstructions.getEditText().setText(intent.getStringExtra("instructions"));
        binding.textEditStreet.getEditText().setText(intent.getStringExtra("street"));
        binding.city.getEditText().setText(intent.getStringExtra("city"));
        binding.province.getEditText().setText(intent.getStringExtra("province"));
        binding.zipCode.getEditText().setText(intent.getStringExtra("codePostal"));
        binding.county.getEditText().setText(intent.getStringExtra("county"));

        binding.bntContinue.setOnClickListener(view1 -> saveNewData());


    }

    private void saveNewData() {

        Intent resultIntent = new Intent();
        resultIntent.putExtra("addressLabel", binding.textEditAddress.getEditText().getText().toString());
        resultIntent.putExtra("instructions", binding.textEditDeliveryInstructions.getEditText().getText().toString());
        resultIntent.putExtra("street", binding.textEditStreet.getEditText().getText().toString());
        resultIntent.putExtra("city", binding.city.getEditText().getText().toString());
        resultIntent.putExtra("province", binding.province.getEditText().getText().toString());
        resultIntent.putExtra("codePostal", binding.zipCode.getEditText().getText().toString());
        resultIntent.putExtra("county", binding.county.getEditText().getText().toString());

        setResult(RESULT_OK, resultIntent);
        finish();


    }

}