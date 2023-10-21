package com.ugdgomezdiez.androidtraining.features.ex02.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel

class GetDogUseCase (private val repository: DogRepository){
    operator fun invoke(): Either<ErrorApp, DogModel>{
        return repository.findDog()
        //hay que crear el view model, hay que usar corrutinas y usar light data
    }
}