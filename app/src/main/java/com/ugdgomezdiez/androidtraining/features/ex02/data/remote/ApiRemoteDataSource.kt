package com.ugdgomezdiez.androidtraining.features.ex02.data.remote

import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.api.ApiClient
import com.ugdgomezdiez.androidtraining.app.api.ApiService
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.apiRemote.toModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository
import retrofit2.Response
import java.io.IOException

class ApiRemoteDataSource:DogRepository{

    private val apiClient: ApiClient = ApiClient()
    override suspend fun findDog(): Either<ErrorApp, Dog> {
        return try {
            val response: Response<DogModel> = apiClient.apiService.getData()
            if (response.isSuccessful) {
                response.body()!!.toModel().right()
            } else {
                ErrorApp.UnknowError.left()
            }
        } catch (e: IOException) {
            ErrorApp.UnknowError.left()
        } catch (e: Exception) {
            ErrorApp.UnknowError.left()
        }
    }

    override suspend fun saveDog(): Either<ErrorApp, Boolean> {
        TODO("Not yet implemented")
    }


}