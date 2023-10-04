package com.ugdgomezdiez.androidtraining.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.domain.UserRepository

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {
    override fun save(name: String, surname: String): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(name, surname)
    }
}