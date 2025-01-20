package com.tuusuario.pizzetos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReseñasAdapter extends RecyclerView.Adapter<ReseñasAdapter.ReseñaViewHolder> {

    private List<PlaceDetailsResponse.Review> reseñas;

    public ReseñasAdapter(List<PlaceDetailsResponse.Review> reseñas) {
        this.reseñas = reseñas;
    }

    @NonNull
    @Override
    public ReseñaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resena, parent, false);
        return new ReseñaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReseñaViewHolder holder, int position) {
        PlaceDetailsResponse.Review reseña = reseñas.get(position);

        holder.authorName.setText(reseña.getAuthorName());
        holder.rating.setText("Calificación: " + reseña.getRating());
        holder.time.setText(reseña.getRelativeTimeDescription());

        traducirTexto(reseña.getText(), new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String textoTraducido = response.body().getData().getTranslations()[0].getTranslatedText();
                    holder.reviewText.setText(textoTraducido);
                } else {
                    holder.reviewText.setText(reseña.getText()); // Fallback al texto original
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                holder.reviewText.setText(reseña.getText()); // Fallback al texto original
            }
        });
    }

    @Override
    public int getItemCount() {
        return reseñas.size();
    }

    static class ReseñaViewHolder extends RecyclerView.ViewHolder {
        TextView authorName, rating, reviewText, time;

        public ReseñaViewHolder(@NonNull View itemView) {
            super(itemView);

            authorName = itemView.findViewById(R.id.tvAuthorName);
            rating = itemView.findViewById(R.id.tvRating);
            reviewText = itemView.findViewById(R.id.tvReviewText);
            time = itemView.findViewById(R.id.tvTime);
        }
    }

    private void traducirTexto(String textoOriginal, Callback<TranslationResponse> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translation.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoogleTranslationApi api = retrofit.create(GoogleTranslationApi.class);
        Call<TranslationResponse> call = api.translate(textoOriginal, "es", "AIzaSyDsRmnOuajB6dxJcgBSp1TY4X5gH6PV31Y");
        call.enqueue(callback);
    }
}
