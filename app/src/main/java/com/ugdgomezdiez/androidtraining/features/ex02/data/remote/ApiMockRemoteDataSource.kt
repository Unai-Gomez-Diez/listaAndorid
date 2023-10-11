package com.ugdgomezdiez.androidtraining.features.ex02.data.remote

import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import java.util.Date

class ApiMockRemoteDataSource {
    fun getDogMock(): Either<ErrorApp,Dog>{
        return Dog("Rami","Un diamante por pulir","hembra", Date()).right()
    }
}