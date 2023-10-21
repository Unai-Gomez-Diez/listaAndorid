package com.ugdgomezdiez.androidtraining.features.ex02.data.remote

import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.api.ApiClient
import com.ugdgomezdiez.androidtraining.app.api.ApiService
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository
import retrofit2.Response
import java.io.IOException

class ApiRemoteDataSource:DogRepository{

    private val apiClient: ApiClient = ApiClient()
    override fun findDog(): Either<ErrorApp, DogModel> {
        return try {
            val response: Response<DogModel> = apiClient.apiService.getData().execute()
            if (response.isSuccessful) {
                response.body()!!.right()
            } else {
                throw RuntimeException()
            }
        } catch (e: IOException) {
            ErrorApp.UnknowError.left()
        } catch (e: Exception) {
            ErrorApp.UnknowError.left()
        }
    }




}