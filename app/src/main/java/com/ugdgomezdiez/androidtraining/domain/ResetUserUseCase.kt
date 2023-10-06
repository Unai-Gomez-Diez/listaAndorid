package com.ugdgomezdiez.androidtraining.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class ResetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return repository.save(input)
    }
}