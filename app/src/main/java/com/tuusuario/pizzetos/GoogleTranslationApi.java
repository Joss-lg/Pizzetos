package com.tuusuario.pizzetos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleTranslationApi {

    @GET("language/translate/v2")
    Call<TranslationResponse> translate(
            @Query("q") String text,               // Texto a traducir
            @Query("target") String targetLanguage, // Idioma objetivo
            @Query("key") String apiKey            // Clave API
    );
}
