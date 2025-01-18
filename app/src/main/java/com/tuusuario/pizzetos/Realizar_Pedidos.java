package com.tuusuario.pizzetos;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class Realizar_Pedidos extends AppCompatActivity {

    private TextInputEditText etNombre, etTelefono, etCantidad, etDomicilio;
    private Spinner spinnerIngredientes, spinnerTamano;
    private RadioGroup radioGroupPago;
    private Button btnEnviar, btnVerPedidos;
    private List<String> listaPedidos = new ArrayList<>(); // Lista para almacenar los pedidos

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
        radioGroupPago = findViewById(R.id.radio_group_pago);
        btnEnviar = findViewById(R.id.btn_enviar);
        btnVerPedidos = findViewById(R.id.btn_ver_pedidos);

        // Configurar Spinner Ingredientes
        ArrayAdapter<CharSequence> adapterIngredientes = ArrayAdapter.createFromResource(
                this, R.array.ingredientes_lista, android.R.layout.simple_spinner_item);
        adapterIngredientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIngredientes.setAdapter(adapterIngredientes);

        // Configurar Spinner Tamaño
        ArrayAdapter<CharSequence> adapterTamano = ArrayAdapter.createFromResource(
                this, R.array.tamano_lista, android.R.layout.simple_spinner_item);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);

        // Configurar acciones de los botones
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarPedido();
            }
        });

        btnVerPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPedidos();
            }
        });
    }

    private void enviarPedido() {
        // Obtener datos del formulario
        String nombre = etNombre.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String domicilio = etDomicilio.getText().toString().trim();
        String ingrediente = spinnerIngredientes.getSelectedItem().toString();
        String tamano = spinnerTamano.getSelectedItem().toString();

        // Verificar si hay algún campo vacío
        if (nombre.isEmpty() || telefono.isEmpty() || cantidad.isEmpty() || domicilio.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener método de pago seleccionado
        int selectedPagoId = radioGroupPago.getCheckedRadioButtonId();
        if (selectedPagoId == -1) {
            Toast.makeText(this, "Seleccione un método de pago", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedPagoButton = findViewById(selectedPagoId);
        String metodoPago = selectedPagoButton.getText().toString();

        // Crear el resumen del pedido
        String pedido = "Nombre: " + nombre + "\n"
                + "Teléfono: " + telefono + "\n"
                + "Ingrediente: " + ingrediente + "\n"
                + "Tamaño: " + tamano + "\n"
                + "Cantidad: " + cantidad + "\n"
                + "Domicilio: " + domicilio + "\n"
                + "Método de Pago: " + metodoPago;

        // Guardar el pedido en la lista
        listaPedidos.add(pedido);

        // Mostrar mensaje final de éxito
        Toast.makeText(this, "Pedido creado con éxito", Toast.LENGTH_SHORT).show();
    }

    private void verPedidos() {
        if (listaPedidos.isEmpty()) {
            Toast.makeText(this, "No hay pedidos realizados", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, Consultar_Pedido.class);
        intent.putStringArrayListExtra("pedidos", new ArrayList<>(listaPedidos));
        startActivity(intent);
    }
}
