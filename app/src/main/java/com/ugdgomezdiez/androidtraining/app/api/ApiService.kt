package com.ugdgomezdiez.androidtraining.app.api

import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex03.data.BurguerApiModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
     @GET("huellas-view.json")
     suspend fun getData(): Response<DogModel>

     @GET("burguer-view.json")
     suspend fun getBurguer(): Response<BurguerApiModel>
}