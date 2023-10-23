package com.ugdgomezdiez.androidtraining.features.ex03.data.remote

import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.api.ApiClient
import com.ugdgomezdiez.androidtraining.features.ex03.data.BurguerApiModel
import com.ugdgomezdiez.androidtraining.features.ex03.data.remote.apiRemote.toModel
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer
import com.ugdgomezdiez.androidtraining.features.ex03.domain.BurguerRepository
import retrofit2.Response
import java.io.IOException

class ApiRemoteDataSource:BurguerRepository {
    private val apiClient: ApiClient = ApiClient()
    override suspend fun findBurguer(): Either<ErrorApp, Burguer> {
        return try {
            val response:Response<BurguerApiModel> = apiClient.apiService.getBurguer()
            if (response.isSuccessful){
                response.body()!!.toModel().right()
            }else{
                ErrorApp.UnknowError.left()
            }
        }catch (e: IOException){
            ErrorApp.UnknowError.left()
        }catch (e: Exception){
            ErrorApp.UnknowError.left()
        }
    }
}