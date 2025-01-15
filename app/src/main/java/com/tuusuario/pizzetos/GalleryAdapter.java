package com.tuusuario.pizzetos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final List<Integer> imageList;

    // Constructor para recibir la lista de imágenes
    public GalleryAdapter(List<Integer> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del ítem (item_gallery.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Establecer la imagen en el ImageView
        int imageResId = imageList.get(position);
        holder.photoImageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return imageList.size(); // Total de elementos en la lista
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;

        ViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }
    }
}
