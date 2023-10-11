package com.ugdgomezdiez.androidtraining.features.ex02.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository

class DogDataRepository: DogRepository {
    override fun findDog(): Either<ErrorApp, Dog> {
        TODO("Not yet implemented")
    }
}