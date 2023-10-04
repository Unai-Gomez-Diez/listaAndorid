package com.ugdgomezdiez.androidtraining.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    fun saveUser(username: String, surname: String): Either<ErrorApp, Boolean> {
        /*val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("surname", surname)
        editor.apply()

        sharedPref.edit().apply{
            putString("username", username)
            putString("surname", surname)
            apply()
        }*/
        return try {
            with(sharedPref.edit()){
                putString("username", username)
                putString("surname", surname)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    }
}