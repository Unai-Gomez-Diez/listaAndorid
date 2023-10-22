package com.ugdgomezdiez.androidtraining.features.ex02.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.api.ApiClient
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import retrofit2.Response
import java.util.Date

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)
    private val apiClient: ApiClient = ApiClient()

    fun setDog():Either<ErrorApp,Boolean>{
        return try {
            val response: Response<DogModel> = apiClient.apiService.getData().execute()

            if (response.isSuccessful) {
                val responseBody = response.body() // Obtén el cuerpo de la respuesta

                if (responseBody != null) {
                    with(sharedPref.edit()) {
                        putString("name", responseBody.name)
                        putString("description", responseBody.short_description)
                        putString("gen", responseBody.sex)
                        putString("dateBorn", responseBody.date_birth)
                        putString("url_image", responseBody.url_image)
                        apply()
                    }
                    true.right()
                } else {
                    ErrorApp.UnknowError.left()
                }
            } else {
                ErrorApp.UnknowError.left()
            }
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }

    }

    fun findDog(): Either<ErrorApp, DogModel>{
        return try {

            DogModel(
                sharedPref.getString("name","")!!,
                sharedPref.getString("description","")!!,
                sharedPref.getString("gen","")!!,
                sharedPref.getString("dateBorn","")!!,
                sharedPref.getString("url_image","")!!

            ).right()
        }catch (ex: java.lang.Exception){
            return  ErrorApp.UnknowError.left()
        }
    }

}

