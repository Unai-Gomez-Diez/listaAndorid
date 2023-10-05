package com.ugdgomezdiez.androidtraining.data.local

import android.content.Context
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.domain.User

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {

        return try {
            with(sharedPref.edit()){
                putString("username", input.username)
                putString("surname", input.surname)
                putString("date", input.age)

                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    }
    fun findUser(): Either<ErrorApp, User> {
        return try {
            User(
                sharedPref.getInt("id", 0),
                sharedPref.getString("username", "")!!,
                sharedPref.getString("surname", "")!!,
                sharedPref.getString("age", "")!!
            ).right()
        } catch (ex: java.lang.Exception) {
            return ErrorApp.UnknowError.left()
        }
    }
}