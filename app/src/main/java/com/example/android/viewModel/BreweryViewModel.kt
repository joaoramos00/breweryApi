package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.Breweries
import com.example.android.service.BreweryAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreweryViewModel(private val service: BreweryAPIService): ViewModel() {

    private val liveData = MutableLiveData<List<Breweries>>()

    fun getBreweries() {

//        viewModelScope.launch(Dispatchers.IO) {
//            val result: List<Breweries> = service.getBreweries()
//
//            liveData.postValue(result)
//        }

    }
}