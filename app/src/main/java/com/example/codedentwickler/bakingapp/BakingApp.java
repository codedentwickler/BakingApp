package com.example.codedentwickler.bakingapp;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class BakingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }
}
