package com.example.android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object {

        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.openbrewerydb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}