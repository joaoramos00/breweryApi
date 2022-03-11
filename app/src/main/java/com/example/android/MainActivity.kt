package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportActionBar?.setLogo(R.drawable.ic_beer)
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayUseLogoEnabled(true);
//        supportActionBar?.setDisplayHomeAsUpEnabled(true);
//        supportActionBar?.setHomeButtonEnabled(true);
        setContentView(R.layout.activity_main)
    }
}