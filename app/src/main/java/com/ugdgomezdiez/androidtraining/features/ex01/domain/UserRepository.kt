package com.ugdgomezdiez.androidtraining.features.ex01.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface UserRepository {

    fun save(user: User): Either<ErrorApp, Boolean>
    fun obtain(): Either<ErrorApp, List<User>>

    fun delete(userId: String): Either<ErrorApp, Boolean>
}