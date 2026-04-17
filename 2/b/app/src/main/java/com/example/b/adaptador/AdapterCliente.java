package com.example.b.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b.R;
import com.example.b.entidades.Cliente;

import java.util.ArrayList;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.ViewHolderClientes>{

    ArrayList<Cliente> listClientes;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void itemClick(Cliente cliente);
    }
    public AdapterCliente(ArrayList<Cliente> list, OnItemClickListener listener) {
        this.listClientes = list;
        this.listener = listener;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado_clientes,null,false);
        return new ViewHolderClientes(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClientes holder, int position) {
        final Cliente item = listClientes.get(position);
        holder.id.setText(listClientes.get(position).getId()+"");
        holder.nom.setText(listClientes.get(position).getNombre());
        holder.ap.setText(listClientes.get(position).getApellido());
        holder.doc.setText(listClientes.get(position).getDocumento());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder{
        TextView id,nom,ap,doc;
        public ViewHolderClientes(View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.itemClientId);
            nom = itemView.findViewById(R.id.itemClientNom);
            ap = itemView.findViewById(R.id.itemClientAp);
            doc = itemView.findViewById(R.id.itemClientDoc);
        }

    }
}
