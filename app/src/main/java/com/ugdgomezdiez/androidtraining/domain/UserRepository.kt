package com.ugdgomezdiez.androidtraining.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface UserRepository {

    fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>
    fun obtain(): Either<ErrorApp, User>

    fun reset(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>
}