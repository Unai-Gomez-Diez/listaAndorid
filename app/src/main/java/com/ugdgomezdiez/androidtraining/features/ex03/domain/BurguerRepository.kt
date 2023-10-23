package com.ugdgomezdiez.androidtraining.features.ex03.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface BurguerRepository {
    suspend fun findBurguer(): Either<ErrorApp, Burguer>
}