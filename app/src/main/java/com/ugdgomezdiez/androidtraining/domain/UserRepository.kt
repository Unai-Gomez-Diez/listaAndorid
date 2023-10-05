package com.ugdgomezdiez.androidtraining.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface UserRepository {

    fun save(input: Input): Either<ErrorApp, Boolean>
}