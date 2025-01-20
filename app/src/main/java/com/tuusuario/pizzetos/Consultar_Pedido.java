package com.tuusuario.pizzetos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Consultar_Pedido extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private Button btnRegresar, btnEnviarWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_pedido);

        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnEnviarWhatsApp = findViewById(R.id.btnEnviarWhatsApp);

        // Obtener la lista de pedidos desde el Intent
        List<String> listaPedidos = getIntent().getStringArrayListExtra("pedidos");

        // Configurar el RecyclerView
        PedidoAdapter adapter = new PedidoAdapter(listaPedidos);
        recyclerViewPedidos.setAdapter(adapter);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        // Acción del botón Regresar
        btnRegresar.setOnClickListener(v -> finish());

        // Acción del botón Enviar pedido a WhatsApp
        btnEnviarWhatsApp.setOnClickListener(v -> {
            if (listaPedidos != null && !listaPedidos.isEmpty()) {
                // Construir el mensaje
                StringBuilder mensaje = new StringBuilder("Estos son mis pedidos:\n");
                for (String pedido : listaPedidos) {
                    mensaje.append("- ").append(pedido).append("\n");
                }

                // Enviar el mensaje por WhatsApp
                String url = "http://wa.me/+5624024759?text=" + Uri.encode(mensaje.toString());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
