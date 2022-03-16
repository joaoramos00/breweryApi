package com.example.android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.NetworkUtils
import com.example.android.service.MapsService
import com.example.android.R
import com.example.android.RecyclerAdapter
import com.example.android.model.Breweries
import com.example.android.service.BreweryAPIService
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id: String = intent.getStringExtra("breweryId").toString()
        onLoad(id)
    }

    fun onLoad(id: String) {
        val btnShowMap = findViewById<ImageButton>(R.id.btnShowMap)
        btnShowMap.setOnClickListener {
            MapsService().openMap(2.7F, 2.7F, this)
        }
        getData(id)

        val btnRateBrewery = findViewById<Button>(R.id.rateBreweryModal)
        btnRateBrewery.setOnClickListener {
            val rateDialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.rate_brewery_layout, null)
            rateDialog.setContentView(view)
            rateDialog.show()
        }
    }

    fun getData(id: String) {
        var brewery:Breweries

        val retrofitClient = NetworkUtils.getRetrofitInstance()

        val endpoint = retrofitClient.create(BreweryAPIService::class.java)
        val callback = endpoint.getBrewery(id)
        callback.enqueue(object : Callback<Breweries> {
            override fun onFailure(call: Call<Breweries>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(
                call: Call<Breweries>,
                response: Response<Breweries>
            ) {
                Toast.makeText(this@DetailsActivity, "Chegou", Toast.LENGTH_LONG)
                brewery = response.body()!!

                findViewById<TextView>(R.id.letter).text =brewery.name?.first().toString()
                findViewById<TextView>(R.id.breweryName).text = brewery.name
                findViewById<TextView>(R.id.rateNumber).text
                findViewById<TextView>(R.id.rateText).text
                findViewById<TextView>(R.id.breweryType).text = brewery.brewery_type
                findViewById<TextView>(R.id.breweryWeb).text = brewery.websiteUrl

                var breweryAddress: String? = brewery.street
                if(brewery.address2 != null) {
                    (breweryAddress + ", " + brewery.address2).also { breweryAddress = it }
                }
                if(brewery.address3 != null) {
                    (breweryAddress + ", " + brewery.address3).also { breweryAddress = it }
                }
                (breweryAddress + ", " + brewery.state + ", " + brewery.postalCode + ", "
                        + brewery.country).also { breweryAddress = it }

                findViewById<TextView>(R.id.breweryAddress).text = breweryAddress

                val ratingBar: RatingBar = findViewById(R.id.ratingBar)
                ratingBar.setOnTouchListener { view, event -> true }
                ratingBar.rating = 3F
            }
        })
    }
}
