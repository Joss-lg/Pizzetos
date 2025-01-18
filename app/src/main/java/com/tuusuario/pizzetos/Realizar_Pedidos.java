package com.tuusuario.pizzetos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Realizar_Pedidos extends AppCompatActivity {

  private TextInputEditText etNombre, etTelefono, etCantidad, etDomicilio;
  private Spinner spinnerIngredientes, spinnerTamano;
  private Button btnEnviar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_realizar_pedidos);

    etNombre = findViewById(R.id.et_nombre);
    etTelefono = findViewById(R.id.et_telefono);
    etCantidad = findViewById(R.id.et_cantidad);
    etDomicilio = findViewById(R.id.et_domicilio);
    spinnerIngredientes = findViewById(R.id.spinner_ingredientes);
    spinnerTamano = findViewById(R.id.spinner_tamano);
    btnEnviar = findViewById(R.id.btn_enviar);

    DatabaseHelper dbHelper = new DatabaseHelper(this);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    insertInitialData(db); // Insertar datos iniciales
    loadSpinnerData(db);  // Cargar datos en los spinners

    btnEnviar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        enviarPedido();
      }
    });
  }

  private void insertInitialData(SQLiteDatabase db) {
    insertIfNotExists(db, "ingredientes", "nombre", "Queso");
    insertIfNotExists(db, "ingredientes", "nombre", "Jamón");
    insertIfNotExists(db, "ingredientes", "nombre", "Pepperoni");
    insertIfNotExists(db, "tamanos", "tamano", "Pequeña ($160)");
    insertIfNotExists(db, "tamanos", "tamano", "Mediana ($235)");
    insertIfNotExists(db, "tamanos", "tamano", "Grande ($295)");
  }

  private void insertIfNotExists(SQLiteDatabase db, String table, String column, String value) {
    Cursor cursor = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE " + column + " = ?", new String[]{value});
    if (!cursor.moveToFirst()) {
      ContentValues values = new ContentValues();
      values.put(column, value);
      db.insert(table, null, values);
    }
    cursor.close();
  }

  private void loadSpinnerData(SQLiteDatabase db) {
    // Ingredientes
    ArrayList<String> ingredientes = getData(db, "ingredientes", "nombre");
    ArrayAdapter<String> adapterIngredientes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientes);
    adapterIngredientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerIngredientes.setAdapter(adapterIngredientes);

    // Tamaños específicos
    ArrayList<String> tamanos = new ArrayList<>(Arrays.asList("Pequeña ($160)", "Mediana ($235)", "Grande ($295)"));
    ArrayAdapter<String> adapterTamanos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanos);
    adapterTamanos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerTamano.setAdapter(adapterTamanos);
  }

  private ArrayList<String> getData(SQLiteDatabase db, String table, String column) {
    ArrayList<String> data = new ArrayList<>();
    Cursor cursor = db.rawQuery("SELECT DISTINCT " + column + " FROM " + table, null); // Usa DISTINCT
    if (cursor.moveToFirst()) {
      do {
        data.add(cursor.getString(0));
      } while (cursor.moveToNext());
    }
    cursor.close();
    return data;
  }

  private void enviarPedido() {
    String nombre = etNombre.getText().toString();
    String telefono = etTelefono.getText().toString();
    String cantidad = etCantidad.getText().toString();
    String domicilio = etDomicilio.getText().toString();

    if (nombre.isEmpty() || telefono.isEmpty() || cantidad.isEmpty() || domicilio.isEmpty()) {
      Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Pedido realizado con éxito", Toast.LENGTH_LONG).show();
    }
  }
}
