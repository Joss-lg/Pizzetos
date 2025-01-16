package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class menuadmin extends AppCompatActivity {

    private CardView cardConsultarPedido, cardCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadmin); // Usa el nombre correcto del layout

        // Enlazar las tarjetas del diseño
        cardConsultarPedido = findViewById(R.id.card_consultar_pedido);
        cardCerrarSesion = findViewById(R.id.card_cerrar_sesion);

        // Listener para la tarjeta de "Consultar Pedido"
        cardConsultarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad para consultar pedidos
                Intent intent = new Intent(menuadmin.this, Pedidos_recientes.class);
                startActivity(intent);
            }
        });

        // Listener para la tarjeta de "Cerrar Sesión"
        cardCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar sesión en Firebase
                FirebaseAuth.getInstance().signOut();

                // Redirigir al login principal
                Intent intent = new Intent(menuadmin.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finaliza la actividad actual
            }
        });
    }
}
