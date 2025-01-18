package com.tuusuario.pizzetos;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Consultar_Pedido extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_pedido);

        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Obtener la lista de pedidos desde el Intent
        List<String> listaPedidos = getIntent().getStringArrayListExtra("pedidos");

        // Configurar el RecyclerView
        PedidoAdapter adapter = new PedidoAdapter(listaPedidos);
        recyclerViewPedidos.setAdapter(adapter);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        // Acción del botón Regresar
        btnRegresar.setOnClickListener(v -> finish());
    }
}
