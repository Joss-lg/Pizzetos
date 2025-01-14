package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    private CardView cardSucursales, cardRealizarPedido, cardConsultarPedido, cardCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Asegúrate de usar el nombre correcto del layout

        // Enlazar las tarjetas del diseño
        cardSucursales = findViewById(R.id.card_sucursales);
        cardRealizarPedido = findViewById(R.id.card_realizar_pedido);
        cardConsultarPedido = findViewById(R.id.card_consultar_pedido);
        cardCerrarSesion = findViewById(R.id.card_cerrar_sesion);

        // Listener para la tarjeta de sucursales
        cardSucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de sucursales
                Intent intent = new Intent(Home.this, Sucursales.class);
                startActivity(intent);
            }
        });

        // Listener para la tarjeta de realizar pedido
        cardRealizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de realizar pedido
                Intent intent = new Intent(Home.this, Realizar_Pedidos.class);
                startActivity(intent);
            }
        });

        // Listener para la tarjeta de consultar pedido
        cardConsultarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de consultar pedido
                Intent intent = new Intent(Home.this, Consultar_Pedido.class);
                startActivity(intent);
            }
        });

        // Listener para la tarjeta de cerrar sesión
        cardCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar sesión y redirigir al inicio de sesión
                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
