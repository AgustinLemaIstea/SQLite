package com.example.educacionit.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import Model.Usuario;
import database.DBHelper;

public class ListActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lvUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setupUI();
        setupButton();
        initializeListView();
    }

    @Override
    protected void onResume() {
        initializeListView();
        super.onResume();
    }

    private void setupUI() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lvUsuarios = (ListView) findViewById(R.id.lvUsuarios);
    }

    private void setupButton() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, ElementEditActivity.class);
                //intent.putExtra("RESULT",resultado);
                startActivity(intent);
            }
        });
    }

    private void initializeListView() {
        DBHelper dbHelper = new DBHelper(ListActivity.this);

        final List<Usuario> usuarios = dbHelper.obtenerUsuarios();

        UsuariosAdapter adapter = new UsuariosAdapter(ListActivity.this, R.layout.item_usuario, usuarios);
        lvUsuarios.setAdapter(adapter);


        lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ListActivity.this, usuarios.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
