package com.ugdgomezdiez.androidtraining.features.ex02.data.remote

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase
import java.util.Date

class ApiMockRemoteDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)

    fun getDogMock(): Either<ErrorApp,Boolean>{
        return try {
            with(sharedPref.edit()){
                putString("name", "Rami")
                putString("descripcion", "Un diamante por pulir")
                putString("sex", "hembra")
                putString("dateBorn", Date().toString())

                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    // return Dog("Rami","Un diamante por pulir","hembra", Date()).right()
    }

}