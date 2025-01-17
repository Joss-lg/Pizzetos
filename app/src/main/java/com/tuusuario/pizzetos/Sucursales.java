package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView; // Asegúrate de importar esto si usas un ImageView para el ícono

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Sucursales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);

        // Referencia al RecyclerView
        RecyclerView galleryRecyclerView = findViewById(R.id.recyclerViewGallery);

        // Configurar el RecyclerView con un GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // 2 columnas
        galleryRecyclerView.setLayoutManager(layoutManager);

        // Preparar la lista de imágenes
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.adentro_6);
        imageList.add(R.drawable.adentro_0);
        imageList.add(R.drawable.adentro_1);
        imageList.add(R.drawable.adentro_2);
        imageList.add(R.drawable.adentro_3);
        imageList.add(R.drawable.adentro_4);
        imageList.add(R.drawable.adentro_5);
        imageList.add(R.drawable.adentro_7);

        // Crear el adaptador y establecerlo en el RecyclerView
        GalleryAdapter adapter = new GalleryAdapter(imageList);
        galleryRecyclerView.setAdapter(adapter);

        // Referencia al botón "Volver"
        Button backButton = findViewById(R.id.btn_regresar);

        // Configurar el OnClickListener para el botón "Volver"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad principal (Home)
                Intent intent = new Intent(Sucursales.this, Home.class);
                startActivity(intent);
                finish(); // Opcional: Para cerrar la actividad actual y evitar que el usuario regrese con el botón atrás
            }
        });

        // Referencia al ícono de ubicación
        ImageView locationIcon = findViewById(R.id.icon_ubicacion); // Asegúrate de que el ID sea correcto en el XML

        // Configurar el OnClickListener para el ícono de ubicación
        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad del mapa
                Intent intent = new Intent(Sucursales.this, Mapa_prueba.class);
                startActivity(intent);
            }
        });
    }
}
