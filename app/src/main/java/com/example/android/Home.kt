package com.example.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.model.Breweries
import com.example.android.service.BreweryAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    private lateinit var recyclerView: RecyclerView
    private lateinit var results: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
//        recyclerView = itemView.findViewById(R.id.recyclerView)
//        results = itemView.findViewById(R.id.results)
//
//        getData()
    }

    private fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.openbrewerydb.org/")

        val endpoint = retrofitClient.create(BreweryAPIService::class.java)
        val callback = endpoint.getBreweries()

        callback.enqueue(object : Callback<List<Breweries>> {
            override fun onFailure(call: Call<List<Breweries>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Breweries>>,
                response: Response<List<Breweries>>
            ) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
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