package com.tp1.myapplication.ui.ciudades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.tp1.myapplication.R;
import com.tp1.myapplication.modelo.Ciudad;

import java.util.List;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.ViewHolderCiudad> {

    private List<Ciudad> ciudades;
    private LayoutInflater li;

    public CiudadAdapter(List<Ciudad> ciudades, LayoutInflater li) {
        this.ciudades = ciudades;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderCiudad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.tarjeta, parent, false);

        return new ViewHolderCiudad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCiudad holder, int position) {
        Ciudad ciudad = ciudades.get(position);
        holder.tvNombre.setText(ciudad.getNombre());
        holder.tvHabitantes.setText(String.valueOf(ciudad.getHabitantes()));
        holder.tvDistancia.setText(String.valueOf(ciudad.getDistancia()));
        holder.foto.setImageResource(ciudad.getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("elegida", ciudad);
                Navigation.findNavController(view).navigate(R.id.detalleCiudadFragment, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ciudades.size();
    }

    public class ViewHolderCiudad extends RecyclerView.ViewHolder {
        TextView tvNombre, tvHabitantes, tvDistancia;
        ImageView foto;

        public ViewHolderCiudad(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvHabitantes = itemView.findViewById(R.id.tvHabitantes);
            tvDistancia = itemView.findViewById(R.id.tvDistancia);
            foto = itemView.findViewById(R.id.foto);
        }
    }
}
