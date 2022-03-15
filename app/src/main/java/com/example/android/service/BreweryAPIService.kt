package com.example.android.service

import com.example.android.model.Breweries
import retrofit2.Call
//import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreweryAPIService {

    @GET("breweries")
//    fun getBreweries(): List<Breweries>
    fun getBreweries(@Query("by_city") city: String): Call<List<Breweries>>
}