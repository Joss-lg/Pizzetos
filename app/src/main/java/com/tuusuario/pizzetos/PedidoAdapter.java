package com.tuusuario.pizzetos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<String> pedidos;

    public PedidoAdapter(List<String> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido_simple, parent, false);

        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        String pedido = pedidos.get(position);
        holder.textViewPedidoNombre.setText("Pedido " + (position + 1)); // Ejemplo de título
        holder.textViewPedidoDetalles.setText(pedido); // Mostrar detalles del pedido
    }


    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPedidoNombre;
        TextView textViewPedidoDetalles;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPedidoNombre = itemView.findViewById(R.id.tvPedidoNombre);
            textViewPedidoDetalles = itemView.findViewById(R.id.tvPedidoDetalles);
        }
    }
}

