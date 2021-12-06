package com.example.l04

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.get
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.OnBackPressedCallback




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setActivityBackButtonVisibility(state: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(state)
    }

    override fun onBackPressed() {
        val navMenu: BottomNavigationView = this.findViewById(R.id.bottom_nav)
        if (navMenu.selectedItemId == R.id.menu_button1) {
            super.onBackPressed()
        }
        else {
            navMenu.selectedItemId = R.id.menu_button1
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}