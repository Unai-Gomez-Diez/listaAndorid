package com.ugdgomezdiez.androidtraining.features.ex03.data.remote.apiRemote

import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex03.data.BurguerApiModel
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer

fun BurguerApiModel.toModel(): Burguer=
    Burguer(this.title,this.discount,this.rate,this.eta,this.urlImage)


