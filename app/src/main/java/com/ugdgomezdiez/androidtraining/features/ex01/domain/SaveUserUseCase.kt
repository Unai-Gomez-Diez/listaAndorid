package com.ugdgomezdiez.androidtraining.features.ex01.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import java.util.Date

class SaveUserUseCase(private val repository: UserRepository) {

    //sustituir por invoke
    operator fun invoke(user: User): Either<ErrorApp, Boolean> {
    return repository.save(user)
    }


    }