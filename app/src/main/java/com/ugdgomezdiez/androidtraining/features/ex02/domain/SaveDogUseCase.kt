package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import java.util.Date

class SaveDogUseCase(private val repository: DogRepository) {
    operator fun invoke(input: Input): Either<ErrorApp, Dog>{
    return repository.saveDog(input)
    }
    data class Input(val name: String, val description: String, val sex: String, val dateBorn: Date)
}