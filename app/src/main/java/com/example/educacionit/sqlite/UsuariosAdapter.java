package com.example.educacionit.sqlite;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Usuario;

/**
 * Created by educacionit on 30/10/2017.
 */

public class UsuariosAdapter extends ArrayAdapter<Usuario> {

    public UsuariosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Usuario> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    //Se ejecuta por cada elemento de la lista
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Usuario usuario = getItem(position);

        //Genero una vista en base a mi xml
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario,null);

        //Accedo al textview de la vista que acabo de generar
        TextView tvNombre = v.findViewById(R.id.tvNombre);
        TextView tvApellido = v.findViewById(R.id.tvApellido);
        TextView tvEmail = v.findViewById(R.id.tvEmail);
        tvNombre.setText(usuario.getNombre());
        tvApellido.setText(usuario.getApellido());
        tvEmail.setText(usuario.getEmail());

        return v;
    }
}
