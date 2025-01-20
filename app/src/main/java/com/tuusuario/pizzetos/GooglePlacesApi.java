package com.tuusuario.pizzetos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesApi {

    @GET("place/details/json")
    Call<PlaceDetailsResponse> getPlaceDetails(
            @Query("place_id") String placeId,     // ID del lugar
            @Query("fields") String fields,        // Campos a recuperar (por ejemplo, "reviews")
            @Query("key") String apiKey            // Clave API
    );
}
