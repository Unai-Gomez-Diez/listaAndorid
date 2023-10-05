package com.ugdgomezdiez.androidtraining.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.domain.Input
import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.domain.UserRepository

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {
    override fun save(input: Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }
}