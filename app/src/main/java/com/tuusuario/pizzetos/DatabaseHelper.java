package com.tuusuario.pizzetos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tuusuario.pizzetos.DatabaseHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PedidosDB";
    private static final int DATABASE_VERSION = 2;

    // Tablas
    private static final String TABLE_INGREDIENTES = "ingredientes";
    private static final String TABLE_TAMANOS = "tamanos";
    private static final String TABLE_PEDIDOS = "pedidos";

    // Crear tabla de ingredientes
    private static final String CREATE_TABLE_INGREDIENTES = "CREATE TABLE " + TABLE_INGREDIENTES + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT NOT NULL)";

    // Crear tabla de tamaños
    private static final String CREATE_TABLE_TAMANOS = "CREATE TABLE " + TABLE_TAMANOS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tamano TEXT NOT NULL)";

    // Crear tabla de pedidos
    private static final String CREATE_TABLE_PEDIDOS = "CREATE TABLE " + TABLE_PEDIDOS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "telefono TEXT, " +
            "cantidad INTEGER, " +
            "ingrediente TEXT, " +
            "tamano TEXT, " +
            "domicilio TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INGREDIENTES);
        db.execSQL(CREATE_TABLE_TAMANOS);
        db.execSQL(CREATE_TABLE_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAMANOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
        onCreate(db);
    }
}
