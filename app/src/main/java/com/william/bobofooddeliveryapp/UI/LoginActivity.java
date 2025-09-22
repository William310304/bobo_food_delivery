package com.william.bobofooddeliveryapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.william.bobofooddeliveryapp.MainActivity;
import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.controller.UsuarioController;
import com.william.bobofooddeliveryapp.databinding.ActivityLoginBinding;
import com.william.bobofooddeliveryapp.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    View view;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // <- ahora binding no será null
        view = binding.getRoot();
        context = this;
        binding.btnLogin.setOnClickListener( view -> btnLogin_click());
        binding.tvRegisterNow.setOnClickListener(view1 -> registerNewUser());
    }

    private void registerNewUser() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }


    private void btnLogin_click() {
        System.out.println("clickkkkkk");
        String txtGmail = binding.edtEmail.getEditText().getText().toString().trim();
        String txtPassword = binding.edtPassword.getEditText().getText().toString().trim();

        if (txtGmail.isEmpty() || txtPassword.isEmpty())
        {
            Snackbar.make(view,"Correo y/o password invalido",Snackbar.LENGTH_LONG).show();
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(txtGmail);
        usuario.setPassword(txtPassword);


        UsuarioController usuarioController = new UsuarioController(context);
        usuarioController.Login(usuario);
        System.out.println("login ? "+usuario.getLogin()+"paswword"+usuario.getPassword()+"correo"+usuario.getCorreo());

        if (usuario.isLogin()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}