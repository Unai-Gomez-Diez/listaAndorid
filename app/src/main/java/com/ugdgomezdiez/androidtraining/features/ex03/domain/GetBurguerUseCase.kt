package com.ugdgomezdiez.androidtraining.features.ex03.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog

class GetBurguerUseCase(private val repository: BurguerRepository) {
    suspend operator fun invoke(): Either<ErrorApp, Burguer> {
        return repository.findBurguer()
    }
}