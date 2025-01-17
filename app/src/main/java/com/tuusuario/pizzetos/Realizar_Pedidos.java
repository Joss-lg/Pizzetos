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

public class Realizar_Pedidos extends AppCompatActivity {

  // Definir vistas
  private TextInputEditText etNombre, etTelefono, etCantidad, etDomicilio;
  private Spinner spinnerIngredientes, spinnerTamano;
  private Button btnEnviar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_realizar_pedidos);

    // Inicializar vistas
    etNombre = findViewById(R.id.et_nombre);
    etTelefono = findViewById(R.id.et_telefono);
    etCantidad = findViewById(R.id.et_cantidad);
    etDomicilio = findViewById(R.id.et_domicilio);
    spinnerIngredientes = findViewById(R.id.spinner_ingredientes);
    spinnerTamano = findViewById(R.id.spinner_tamano);
    btnEnviar = findViewById(R.id.btn_enviar);

    // Configurar la base de datos
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    // Insertar datos iniciales
    insertInitialData(db);

    // Cargar datos en los spinners
    loadSpinnerData(db);

    // Configurar acción del botón
    btnEnviar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        enviarPedido();
      }
    });
  }

  // Insertar datos iniciales en SQLite
  private void insertInitialData(SQLiteDatabase db) {
    insertIngrediente(db, "Queso");
    insertIngrediente(db, "Jamón");
    insertIngrediente(db, "Pepperoni");
    insertTamano(db, "Pequeño");
    insertTamano(db, "Mediano");
    insertTamano(db, "Grande");
  }

  private void insertIngrediente(SQLiteDatabase db, String nombre) {
    ContentValues values = new ContentValues();
    values.put("nombre", nombre);
    db.insert("ingredientes", null, values);
  }

  private void insertTamano(SQLiteDatabase db, String tamano) {
    ContentValues values = new ContentValues();
    values.put("tamano", tamano);
    db.insert("tamanos", null, values);
  }

  // Cargar datos en los spinners
  private void loadSpinnerData(SQLiteDatabase db) {
    ArrayList<String> ingredientes = getData(db, "ingredientes", "nombre");
    ArrayAdapter<String> adapterIngredientes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientes);
    adapterIngredientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerIngredientes.setAdapter(adapterIngredientes);

    ArrayList<String> tamanos = getData(db, "tamanos", "tamano");
    ArrayAdapter<String> adapterTamanos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanos);
    adapterTamanos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerTamano.setAdapter(adapterTamanos);
  }

  private ArrayList<String> getData(SQLiteDatabase db, String table, String column) {
    ArrayList<String> data = new ArrayList<>();
    Cursor cursor = db.rawQuery("SELECT " + column + " FROM " + table, null);
    if (cursor.moveToFirst()) {
      do {
        data.add(cursor.getString(0));
      } while (cursor.moveToNext());
    }
    cursor.close();
    return data;
  }

  // Acción para el botón "Enviar Pedido"
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
