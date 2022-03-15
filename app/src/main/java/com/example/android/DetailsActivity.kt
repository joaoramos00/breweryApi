package com.example.android

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import java.security.AccessControlContext
import java.security.AccessController.getContext

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        onLoad()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onLoad() {
        val btnShowMap = findViewById<ImageButton>(R.id.btnShowMap)
        btnShowMap.setOnClickListener {
            Maps().openMap(2.7F,2.7F, this@DetailsActivity)
        }
        findViewById<TextView>(R.id.breweryName).text = Data().breweryName
        findViewById<TextView>(R.id.rateNumber).text = Data().breweryRate
        findViewById<TextView>(R.id.rateText).text = Data().breweryRateString
        findViewById<TextView>(R.id.breweryType).text = Data().breweryType
        findViewById<TextView>(R.id.breweryWeb).text = Data().breweryWeb
        findViewById<TextView>(R.id.breweryAddress).text = Data().breweryAddress
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        ratingBar.setOnTouchListener { view, event -> true }
        ratingBar.rating = 3F
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

}