package com.ugdgomezdiez.androidtraining.features.ex02.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)

    fun setDog(input: SaveDogUseCase.Input):Either<ErrorApp,Boolean>{
        return try {
            with(sharedPref.edit()){
                putString("name",input.name)
                putString("description",input.description)
                putString("gen",input.sex)
                putString("dateBorn",input.dateBorn.toString())

                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }

    }

    fun findDog(): Either<ErrorApp, Dog>{
        return try {

            Dog(
                sharedPref.getString("name","")!!,
                sharedPref.getString("description","")!!,
                sharedPref.getString("gen","")!!,
                Date(sharedPref.getString("dateBorn","")!!)

            ).right()
        }catch (ex: java.lang.Exception){
            return  ErrorApp.UnknowError.left()
        }
    }

}

