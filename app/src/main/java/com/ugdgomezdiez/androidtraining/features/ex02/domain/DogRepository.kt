package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel

interface DogRepository {
    fun findDog(): Either<ErrorApp, DogModel>
    fun saveDog(): Either<ErrorApp, Boolean>
}