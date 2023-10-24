package com.ugdgomezdiez.androidtraining.features.ex01.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex01.data.local.XmlLocalDataSource

import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User
import com.ugdgomezdiez.androidtraining.features.ex01.domain.UserRepository

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {
    override fun save(user: User): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(user)
    }



    override fun obtain(): Either<ErrorApp, List<User>> {
        return localDataSource.findUser()
    }

    override fun delete(userId: String): Either<ErrorApp, Boolean> {
        return localDataSource.removeUserById(userId)
    }
}