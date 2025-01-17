package com.tuusuario.pizzetos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Realizar_Pedidos extends AppCompatActivity {

    private TextInputEditText etNombre, etTelefono, etCantidad, etDomicilio;
    private Spinner spinnerIngredientes, spinnerTamano;
    private RadioGroup radioGroupPago;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedidos); // Asegúrate de usar el nombre correcto del layout

        // Enlazar vistas
        etNombre = findViewById(R.id.et_nombre);
        etTelefono = findViewById(R.id.et_telefono);
        etCantidad = findViewById(R.id.et_cantidad);
        etDomicilio = findViewById(R.id.et_domicilio);
        spinnerIngredientes = findViewById(R.id.spinner_ingredientes);
        spinnerTamano = findViewById(R.id.spinner_tamano);
        radioGroupPago = findViewById(R.id.radio_group_pago);
        btnEnviar = findViewById(R.id.btn_enviar);

        // Configurar el spinner de ingredientes
        String[] ingredientes = {"Hawaiana", "Mexicana", "Pastor", "Azteca", "Carnes Frias", "Cubana", "Costilla BBQ"};
        ArrayAdapter<String> adapterIngredientes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientes);
        adapterIngredientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIngredientes.setAdapter(adapterIngredientes);

        // Configurar el spinner de tamaños
        String[] tamanos = {"Chica", "Mediana", "Grande", "Familiar"};
        ArrayAdapter<String> adapterTamano = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanos);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);

        // Configurar botón enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarPedido();
            }
        });
    }

    private void enviarPedido() {
        // Obtener datos del formulario
        String nombre = etNombre.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();
        String domicilio = etDomicilio.getText().toString().trim();
        String ingrediente = spinnerIngredientes.getSelectedItem().toString();
        String tamano = spinnerTamano.getSelectedItem().toString();
        int pagoId = radioGroupPago.getCheckedRadioButtonId();

        // Validar datos
        if (nombre.isEmpty() || telefono.isEmpty() || cantidadStr.isEmpty() || domicilio.isEmpty() || pagoId == -1) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "La cantidad debe ser un número válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Determinar método de pago
        RadioButton selectedPago = findViewById(pagoId);
        String metodoPago = selectedPago.getText().toString();

        // Simular el envío del pedido
        String mensaje = "Pedido realizado:\n" +
                "Nombre: " + nombre + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Ingrediente: " + ingrediente + "\n" +
                "Tamaño: " + tamano + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Domicilio: " + domicilio + "\n" +
                "Pago: " + metodoPago;

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        // Aquí podrías implementar la lógica para enviar el pedido al servidor
    }
}
