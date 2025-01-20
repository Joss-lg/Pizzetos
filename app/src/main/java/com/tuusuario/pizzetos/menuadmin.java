package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class menuadmin extends AppCompatActivity {

    private CardView cardConsultarPedido, cardCerrarSesion;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadmin); // Asegúrate de usar el layout correcto

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Enlazar las tarjetas del diseño
        cardConsultarPedido = findViewById(R.id.card_consultar_pedido);
        cardCerrarSesion = findViewById(R.id.card_cerrar_sesion);

        // Listener para la tarjeta de "Consultar Pedido"
        cardConsultarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarPedidosYRedirigir();
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

    private void cargarPedidosYRedirigir() {
        // Simula la consulta a la base de datos (Firebase Firestore)
        db.collection("pedidos")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<String> listaPedidos = new ArrayList<>();

                    // Extraer los datos de los pedidos
                    queryDocumentSnapshots.forEach(documentSnapshot -> {
                        String pedido = documentSnapshot.getString("descripcion");
                        listaPedidos.add(pedido);
                    });

                    // Redirigir a Consultar_Pedido y pasar la lista de pedidos
                    Intent intent = new Intent(menuadmin.this, Consultar_Pedido.class);
                    intent.putStringArrayListExtra("pedidos", listaPedidos);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    // Manejar errores al cargar pedidos
                    e.printStackTrace();
                });
    }
}
