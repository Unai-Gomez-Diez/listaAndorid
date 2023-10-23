package com.ugdgomezdiez.androidtraining.features.ex02.data.remote.apiRemote

import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog

fun DogModel.toModel(): Dog =
    Dog(this.name, this.short_description, this.sex, this.date_birth,this.url_image)