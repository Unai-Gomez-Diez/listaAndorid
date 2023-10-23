package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import java.util.Date

class SaveDogUseCase(private val repository: DogRepository) {
    suspend operator fun invoke(): Either<ErrorApp, Boolean>{
    return repository.saveDog()
    }
    data class Input(val name: String, val description: String, val sex: String, val dateBorn: Date)
}