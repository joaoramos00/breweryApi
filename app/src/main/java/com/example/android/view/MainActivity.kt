package com.example.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.NetworkUtils
import com.example.android.R
import com.example.android.RecyclerAdapter
import com.example.android.model.Breweries
import com.example.android.service.BreweryAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var results: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setLogo(R.drawable.ic_beer)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        recyclerView = findViewById(R.id.mainRecyclerView)
        results = findViewById(R.id.results)

    }

    private fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.openbrewerydb.org/")

        val endpoint = retrofitClient.create(BreweryAPIService::class.java)
        val callback = endpoint.getBreweries()

        callback.enqueue(object : Callback<List<Breweries>> {
            override fun onFailure(call: Call<List<Breweries>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Breweries>>,
                response: Response<List<Breweries>>
            ) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    response.body()?.let {
                        results.text = "Exibindo ${it.size} resultados"
                        adapter = RecyclerAdapter(it)
                    }

                }
//                response.body()?.forEach {
//                    Toast.makeText(context, it.city, Toast.LENGTH_SHORT).show()
//                }
            }
        })

    }
}