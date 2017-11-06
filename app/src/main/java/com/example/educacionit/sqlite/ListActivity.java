package com.example.educacionit.sqlite;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
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
    private List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setupUI();
        setupButton();
        initializeListView();
        showNotification();
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

        usuarios = dbHelper.obtenerUsuarios();

        UsuariosAdapter adapter = new UsuariosAdapter(ListActivity.this, R.layout.item_usuario, usuarios);
        lvUsuarios.setAdapter(adapter);


        lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ListActivity.this, usuarios.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ListActivity.this);
        builder.setContentTitle("Bienvenido de vuelta")
                .setContentText(String.format("Tenés %d usuarios registrados. Agrega uno más!",usuarios.size()))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);


        Intent intent = new Intent(ListActivity.this, ElementEditActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(ListActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//pending intent
        builder.setContentIntent(pendingIntent);

        NotificationManager managerCompat = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//obtengo el servicio de notificaciones y se lo pongo al manager
        managerCompat.notify(1, builder.build());    //le paso un id y el builder

    }
}
