package com.william.bobofooddeliveryapp.controller;

import android.content.Context;

import com.william.bobofooddeliveryapp.db.Db;
import com.william.bobofooddeliveryapp.model.Usuario;

public class UsuarioController {
    Db db;

    public UsuarioController(Context context) {
        db = new Db(context);
    }

    public void Login(Usuario usuario) {
        System.out.println(usuario.getCorreo()+"--"+usuario.getPassword());
        if (usuario.getCorreo().equals("william@123.com") && usuario.getPassword().equals("william123")){
            System.out.println("OK");
//            usuario.isLogin();
            usuario.setLogin(1);
        }
        return;
    }
}
