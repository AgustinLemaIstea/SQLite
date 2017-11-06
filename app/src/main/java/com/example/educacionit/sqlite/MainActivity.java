package com.example.educacionit.sqlite;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        crearNotificacion();
    }

    private void crearNotificacion(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("titulo")
                .setContentText("texto....asdasf...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("mostrando notificacion..")
                .setPriority(Notification.PRIORITY_MAX)
                .setVibrate(new long[] {1000, 1000});


        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//pending intent
        builder.setContentIntent(pendingIntent);

        NotificationManager managerCompat = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//obtengo el servicio de notificaciones y se lo pongo al manager
        managerCompat.notify((int)(Math.random()*1000)+1, builder.build());    //le paso un id y el builder

    }
}
