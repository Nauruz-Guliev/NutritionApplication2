package com.example.nutritionapplication.dataBase.firebaseDatabase

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyFirebaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}