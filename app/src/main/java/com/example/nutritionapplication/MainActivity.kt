package com.example.nutritionapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    //https://coolors.co/ff6666-c6ff00-5d2e8c-6d4c41-2ec4b6


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.hide()

        val btmNavView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        setupActionBarWithNavController(navController)
        btmNavView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }

    fun hideBottomNav() {
        val btmNavView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        btmNavView.visibility = View.GONE
    }

    fun showBottomNav() {
        val btmNavView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        btmNavView.visibility = View.VISIBLE
    }

}