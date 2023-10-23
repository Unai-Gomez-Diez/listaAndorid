package com.ugdgomezdiez.androidtraining.features.ex03.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.api.ApiClient
import com.ugdgomezdiez.androidtraining.app.serialization.JsonSerialization
import com.ugdgomezdiez.androidtraining.features.ex03.data.BurguerApiModel
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer
import retrofit2.Response

class XmlLocalDataSource (
    private val context: Context,
    private val serialization: JsonSerialization
){
    private val sharedPref = context.getSharedPreferences("burguer", Context.MODE_PRIVATE)
    private val burguerId = "1"
    private val apiClient: ApiClient = ApiClient()

    fun getBurguer(): Either<ErrorApp, Burguer>{
        val jsonBurguer = sharedPref.getString(burguerId, null)
        jsonBurguer?.let {
            return serialization.fromJson(it, Burguer::class.java).right()
        }
        return ErrorApp.UnknowError.left()
    }

    fun save(burguer: Burguer){
        sharedPref.edit().apply(){
            putString(burguerId, serialization.toJson(burguer, Burguer::class.java))
            apply()
        }
    }
}