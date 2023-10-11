package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class GetDogUseCase (private val repository: DogRepository){
    operator fun invoke(): Either<ErrorApp, Dog>{
        return repository.findDog()
        //hay que crear el view model, hay que usar corrutinas y usar light data
    }
}