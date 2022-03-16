package com.example.android.viewModel//package com.example.android.viewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.android.service.BreweryAPIService
//
//class BreweryViewModelFactory(
//    private val apiService: BreweryAPIService
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BreweryViewModel::class.java)) {
//            return BreweryViewModel(apiService) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
