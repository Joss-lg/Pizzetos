package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private CardView cardSucursales, cardRealizarPedido, cardReseñas, cardCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Asegúrate de que este layout exista

        // Enlazar las tarjetas del diseño con sus IDs
        cardSucursales = findViewById(R.id.card_sucursales);
        cardRealizarPedido = findViewById(R.id.card_realizar_pedido);
        cardReseñas = findViewById(R.id.card_reseñas);
        cardCerrarSesion = findViewById(R.id.card_cerrar_sesion);

        // Configurar eventos de clic para cada tarjeta
        cardSucursales.setOnClickListener(v -> {
            // Ir a la actividad de sucursales
            startActivity(new Intent(Home.this, Sucursales.class));
        });

        cardRealizarPedido.setOnClickListener(v -> {
            // Ir a la actividad de realizar pedidos
            startActivity(new Intent(Home.this, Realizar_Pedidos.class));
        });

        cardReseñas.setOnClickListener(v -> {
            // Ir a la actividad de reseñas
            startActivity(new Intent(Home.this, ActivityResenas.class));
        });

        cardCerrarSesion.setOnClickListener(v -> {
            // Cerrar sesión de Firebase y redirigir al inicio de sesión
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Home.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Finaliza esta actividad
        });
    }
}
