package com.ugdgomezdiez.androidtraining.features.ex02.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import java.util.Date

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)

    fun setDog(dogModel: DogModel):Either<ErrorApp,Boolean>{
        return try {
            with(sharedPref.edit()){
                putString("name",dogModel.name)
                putString("description",dogModel.short_description)
                putString("gen",dogModel.sex)
                putString("dateBorn",dogModel.date_birth)
                putString("url_image",dogModel.url_image)

                apply()
            }
            true.right()
        }catch (ex: Exception){
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

