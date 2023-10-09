package com.ugdgomezdiez.androidtraining.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.data.local.XmlLocalDataSource

import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.domain.User
import com.ugdgomezdiez.androidtraining.domain.UserRepository

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {
    override fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }

    override fun obtain(): Either<ErrorApp, User> {
        return localDataSource.findUser(1)
    }

    override fun reset(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }
}