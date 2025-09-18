package com.william.bobofooddeliveryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    SQLiteDatabase database;
    String sql;

    public Db(@Nullable Context context ){
        super(context, "boboFood.db", null, 1);
        database = getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuario (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    correo TEXT UNIQUE NOT NULL,\n" +
                "    passwordd TEXT NOT NULL,\n" +
                "    phone TEXT,\n" +
                "    dateOfBirth TEXT,\n" +
                "    addressLabel TEXT,\n" +
                "    deliveryInstructions TEXT,\n" +
                "    fullName TEXT,\n" +
                "    streetAddress TEXT,\n" +
                "    city TEXT,\n" +
                "    province TEXT,\n" +
                "    postalCode TEXT,\n" +
                "    country TEXT,\n" +
                "    idSubcription INTEGER,\n" +
                "    fotoPerfil TEXT,\n" +
                "    estado TEXT\n" +
                ");");

        db.execSQL("CREATE TABLE Rol (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nombre TEXT NOT NULL UNIQUE\n" +
                ");");

        db.execSQL("CREATE TABLE usersRoles (\n" +
                "    userId INTEGER NOT NULL,\n" +
                "    rolId INTEGER NOT NULL,\n" +
                "    PRIMARY KEY(userId, rolId),\n" +
                "    FOREIGN KEY (userId) REFERENCES Usuario(id),\n" +
                "    FOREIGN KEY (rolId) REFERENCES Rol(id)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Subcription (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    plann TEXT,\n" +
                "    precio REAL,\n" +
                "    tiempo TEXT,\n" +
                "    usuarioId INTEGER,\n" +
                "    FOREIGN KEY (usuarioId) REFERENCES Usuario(id)\n" +
                ");");
        db.execSQL("CREATE TABLE Card (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idUsuario INTEGER,\n" +
                "    cardNumber TEXT,\n" +
                "    cvv TEXT,\n" +
                "    expirationDate TEXT,\n" +
                "    cardholderName TEXT,\n" +
                "    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)\n" +
                ");");
        db.execSQL("CREATE TABLE Cupon (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    typo TEXT,\n" +
                "    descripcion TEXT\n" +
                ");");
        db.execSQL("CREATE TABLE Product (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    imgProducto TEXT,\n" +
                "    estrella INTEGER,\n" +
                "    kcal INTEGER CHECK (kcal >= 0),\n" +
                "    mins INTEGER,\n" +
                "    nombre TEXT,\n" +
                "    descripcion TEXT,\n" +
                "    precio_U REAL,\n" +
                "    categoria TEXT\n" +
                ");");
        db.execSQL("CREATE TABLE My_order (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idUsuario INTEGER NOT NULL,\n" +
                "    totalPrecio REAL,\n" +
                "    fecha TEXT DEFAULT (datetime('now')),\n" +
                "    idCupon INTEGER,\n" +
                "    FOREIGN KEY (idUsuario) REFERENCES Usuario(id),\n" +
                "    FOREIGN KEY (idCupon) REFERENCES Cupon(id)\n" +
                ");");
        db.execSQL("CREATE TABLE pedidosProductos (\n" +
                "    myOrderId INTEGER,\n" +
                "    productId INTEGER,\n" +
                "    cantidad INTEGER DEFAULT 1,\n" +
                "    PRIMARY KEY (myOrderId, productId),\n" +
                "    FOREIGN KEY (myOrderId) REFERENCES My_order(id),\n" +
                "    FOREIGN KEY (productId) REFERENCES Product(id)\n" +
                ");");
        db.execSQL("CREATE TABLE Favourites (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idProducto INTEGER,\n" +
                "    idUsuario INTEGER,\n" +
                "    FOREIGN KEY (idProducto) REFERENCES Product(id),\n" +
                "    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)\n" +
                ");");
        db.execSQL("CREATE TABLE Ubicacion (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    latitud REAL,\n" +
                "    longitud REAL,\n" +
                "    tipo TEXT CHECK (tipo IN ('origen', 'destino')),\n" +
                "    timestamp TEXT DEFAULT (datetime('now')),\n" +
                "    myOrderId INTEGER,\n" +
                "    FOREIGN KEY (myOrderId) REFERENCES My_order(id)\n" +
                ");");
        db.execSQL("CREATE TABLE Repartidor (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    fullName TEXT,\n" +
                "    correo TEXT UNIQUE NOT NULL,\n" +
                "    passwordd TEXT NOT NULL,\n" +
                "    myOrderId INTEGER,\n" +
                "    FOREIGN KEY (myOrderId) REFERENCES My_order(id)\n" +
                ");");

    }

    public void sentencia(String sql){
        this.sql = sql;
    }
    public Cursor getCursor(){
        return database.rawQuery(this.sql,null);
    }

    public int insert(String usuario, ContentValues values){
        return (int)database.insert(usuario,null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
