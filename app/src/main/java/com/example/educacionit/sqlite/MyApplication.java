package com.example.educacionit.sqlite;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by educacionit on 23/10/2017.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
