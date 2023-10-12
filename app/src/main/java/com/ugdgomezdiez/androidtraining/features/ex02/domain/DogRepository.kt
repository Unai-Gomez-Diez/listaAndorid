package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface DogRepository {
    fun findDog(): Either<ErrorApp, Dog>
    fun saveDog(input: SaveDogUseCase.Input): Either<ErrorApp, Boolean>
}