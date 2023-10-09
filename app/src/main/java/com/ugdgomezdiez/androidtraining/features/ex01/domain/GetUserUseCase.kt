package com.ugdgomezdiez.androidtraining.features.ex01.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class GetUserUseCase(private  val repository: UserRepository) {

    operator fun invoke(): Either<ErrorApp, User>{
        return repository.obtain()
    }

}