package com.ugdgomezdiez.androidtraining.features.ex02.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiMockRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase

class DogDataRepository(
    val localSource: XmlLocalDataSource,
    val apiSource: ApiMockRemoteDataSource
): DogRepository{
    override fun findDog(): Either<ErrorApp, Dog> {
        val resultLocal= localSource.findDog()

        if(resultLocal.isRight()){
            return resultLocal
        }
        return apiSource.getDogMock().map {
            localSource.setDog(it)
            it
        }
    }

    override fun saveDog(input: SaveDogUseCase.Input): Either<ErrorApp, Dog> {
        return apiSource.getDogMock()
    }
}


