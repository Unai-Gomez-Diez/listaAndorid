package com.ugdgomezdiez.androidtraining.features.ex03.data

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex03.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex03.data.remote.ApiRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer
import com.ugdgomezdiez.androidtraining.features.ex03.domain.BurguerRepository

class BurguerDataRepository(
    private val local: XmlLocalDataSource,
    private val remote: ApiRemoteDataSource
): BurguerRepository{
    override suspend fun findBurguer(): Either<ErrorApp, Burguer> {
        var burguer = local.getBurguer()
        burguer.mapLeft {
            return remote.findBurguer().map {
                local.save(it)
                it
            }
        }
        return burguer
    }
}