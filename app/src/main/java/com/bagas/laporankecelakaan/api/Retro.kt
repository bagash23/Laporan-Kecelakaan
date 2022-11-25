package com.bagas.laporankecelakaan.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance() : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://08bb-180-252-116-12.ap.ngrok.io/accident/public/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}