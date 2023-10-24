package com.ugdgomezdiez.androidtraining.features.ex01.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class DeleteUserUseCase(private val repository: UserRepository) {

    operator fun invoke(userId:String): Either<ErrorApp, Boolean> {
        return repository.delete(userId)
    }
}