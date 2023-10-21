package com.ugdgomezdiez.androidtraining.features.ex02.data

import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiMockRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase

class DogDataRepository(
    val localSource: XmlLocalDataSource,
    val apiSource: ApiRemoteDataSource
): DogRepository{
    override fun findDog(): Either<ErrorApp, DogModel> {
        val resultLocal= localSource.findDog()

        if(resultLocal.isRight()){
            return resultLocal
        }
        return apiSource.findDog().map {
            localSource.setDog(it)
            it
        }
    }



    override fun saveDog(): Either<ErrorApp, Boolean> {
        apiSource.findDog()
        return true.right()
    }
}


