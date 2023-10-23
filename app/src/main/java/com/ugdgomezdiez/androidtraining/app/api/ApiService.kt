package com.ugdgomezdiez.androidtraining.app.api

import retrofit2.Call
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
     @GET("huellas-view.json")
     suspend fun getData(): Response<DogModel>
}