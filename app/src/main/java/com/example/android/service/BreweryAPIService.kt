package com.example.android.service

import com.example.android.model.Breweries
import retrofit2.Call
//import retrofit2.Call
import retrofit2.http.GET

interface BreweryAPIService {

    @GET("breweries?by_city=san_diego")
//    fun getBreweries(): List<Breweries>
    fun getBreweries(): Call<List<Breweries>>
}