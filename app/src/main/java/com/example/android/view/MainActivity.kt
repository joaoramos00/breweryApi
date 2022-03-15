package com.example.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
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
    private lateinit var searchBtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setLogo(R.drawable.ic_beer)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        recyclerView = findViewById(R.id.mainRecyclerView)
        results = findViewById(R.id.results)

        searchBtn = findViewById(R.id.search_button)

        searchBtn.setOnClickListener {
            val city: EditText = findViewById(R.id.search_city)
            getData(city.text.toString())
        }

    }

    private fun getData(city: String) {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()

        val list = findViewById<LinearLayout>(R.id.list)
        val empty = findViewById<LinearLayout>(R.id.empty)
        val noResults = findViewById<LinearLayout>(R.id.noResults)

        val value = city.lowercase().trim().replace(" ", "_")

        if (value.isEmpty()) {
            empty.visibility = View.VISIBLE
            list.visibility = View.GONE
            noResults.visibility = View.GONE
            return
        }

        val endpoint = retrofitClient.create(BreweryAPIService::class.java)
        val callback = endpoint.getBreweries(value)

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

//                    Toast.makeText(context, "${response.body()}", Toast.LENGTH_LONG).show()

                    if (response.body()?.size?.equals(0) == true) {
                        noResults.visibility = View.VISIBLE
                        list.visibility = View.GONE
                        empty.visibility = View.GONE
                        return
                    }

                    response.body()?.let {
                        results.text = "Exibindo ${it.size} resultados"
                        adapter = RecyclerAdapter(it)
                        list.visibility = View.VISIBLE
                        noResults.visibility = View.GONE
                        empty.visibility = View.GONE
                    }

                }
//                response.body()?.forEach {
//                    Toast.makeText(context, it.city, Toast.LENGTH_SHORT).show()
//                }
            }
        })

    }
}