package com.tuusuario.pizzetos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityResenas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReseñasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenas);

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerViewReseñas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener las reseñas desde la API
        obtenerReseñas();
    }

    private void obtenerReseñas() {
        // Inicializar Retrofit
        Retrofit retrofit = ApiClient.getClient();
        GooglePlacesApi googlePlacesApi = retrofit.create(GooglePlacesApi.class);

        // Reemplaza con tu Place ID y clave API
        String placeId = "ChIJSVWDBIgjzoURaBS2djMchUM"; // ID del lugar
        String apiKey = "AIzaSyDGMMvhCoyS-aYBfSjdBdytNbFQyufao68";  // Clave API

        // Llamada a la API para obtener las reseñas
        googlePlacesApi.getPlaceDetails(placeId, "reviews", apiKey).enqueue(new Callback<PlaceDetailsResponse>() {
            @Override
            public void onResponse(Call<PlaceDetailsResponse> call, Response<PlaceDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Obtener las reseñas
                    List<PlaceDetailsResponse.Review> reviews = response.body().getResult().getReviews();
                    // Configurar el adaptador
                    adapter = new ReseñasAdapter(reviews);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ActivityResenas.this, "Error al obtener reseñas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlaceDetailsResponse> call, Throwable t) {
                Toast.makeText(ActivityResenas.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
