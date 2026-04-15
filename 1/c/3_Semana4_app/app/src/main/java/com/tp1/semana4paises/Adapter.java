package com.tp1.semana4paises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Modelo>{

    private List<Modelo> mList;

    private Context mContext;

    private int resourceLayout;

    public Adapter(@NonNull Context context, int resource, @NonNull List<Modelo> objects) {
        super(context, resource, objects);

        this.mList = objects;
        this.mContext = context;
        this.resourceLayout = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        // Evaluar si no está vacio

        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.lugares, null);
        }

        Modelo modelo = mList.get(position);
        ImageView imagen = view.findViewById(R.id.imvLugar);
        imagen.setImageResource(modelo.getImagen());

        TextView titulo = view.findViewById(R.id.txvTitulo);
        titulo.setText(modelo.getTitulo());

        TextView contenido = view.findViewById(R.id.txvContenido);
        contenido.setText(modelo.getContenido());



        return view;
    }
}
