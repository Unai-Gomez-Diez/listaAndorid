package com.ugdgomezdiez.androidtraining.app.api

import android.telecom.Call
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
     @GET("huellas-view.json")
     fun getData(): Call<DogApiModel>
}