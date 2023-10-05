package com.ugdgomezdiez.androidtraining.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import java.util.Date

class SaveUserUseCase(private val repository: UserRepository) {

    //sustituir por invoke
    operator fun invoke(input: Input): Either<ErrorApp, Boolean> {
    return repository.save(input)
    }


    data class Input(val username: String, val surname: String, val age: String)
}