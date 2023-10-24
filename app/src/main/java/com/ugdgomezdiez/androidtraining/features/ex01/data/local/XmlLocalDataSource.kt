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


    fun saveUser(user: User): Either<ErrorApp, Boolean> {

        return try {
            with(sharedPref.edit()){
                val id=(1..100).random()
                val user = User(id, user.username,user.surname)
                val jsonUser =gson.toJson(user, User::class.java)
                putString(id.toString(), jsonUser)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            ErrorApp.UnknowError.left()
        }


    }
    fun findUser(): Either<ErrorApp, List<User>> {
        return try {
            val jsonUsers= sharedPref.all as Map<String, String>
            val users=jsonUsers.values.map {
                gson.fromJson(it, User::class.java)
            }
            return users.right()
        } catch (ex: java.lang.Exception) {
            ErrorApp.UnknowError.left()
        }
    }


    fun removeUserById(userId: String):Either<ErrorApp,Boolean>{
        return try{
            sharedPref.edit().remove(userId).apply()
            true.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }

    }

}