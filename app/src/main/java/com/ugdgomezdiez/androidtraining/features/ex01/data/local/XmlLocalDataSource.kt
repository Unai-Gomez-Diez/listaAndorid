package com.ugdgomezdiez.androidtraining.features.ex01.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User

class XmlLocalDataSource (private val context: Context){
    val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    private val gson = Gson()

    init {
        sharedPref.edit().remove("users").apply()
    }
    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {

        return try {
            with(sharedPref.edit()){
                val id=(1..100).random()
                val user = User(id, input.username,input.surname)
                val jsonUser =gson.toJson(user, User::class.java)
                putString(id.toString(), jsonUser)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    }
    fun findUser(userId: Int): Either<ErrorApp, User> {
        return try {
            val jsonUser = sharedPref.getString(userId.toString(),"{}")
            gson.fromJson(jsonUser, User::class.java).right()

        } catch (ex: java.lang.Exception) {
            ErrorApp.UnknowError.left()
        }
    }

    fun resetUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {

        return try {
            with(sharedPref.edit()){
                putString("username", "")
                putString("surname", "")

                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    }
    fun removeUserById(userId: Int){
        //primer string es el UserId y el segundo es jsonUser
        val mapUser: Map<String,String> = sharedPref.all as Map<String,String>
        //recuperar usuarios de una lista
        //eliminar el usuario de la lista
        //serializar usuario a usuario y guardarlo en el xml
        mapUser
    }

}