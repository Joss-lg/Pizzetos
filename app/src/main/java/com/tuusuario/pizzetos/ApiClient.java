package com.tuusuario.pizzetos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/") // URL base de la API
                    .addConverterFactory(GsonConverterFactory.create()) // Convertidor de JSON a objetos
                    .build();
        }
        return retrofit;
    }
}
