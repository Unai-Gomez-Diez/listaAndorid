package com.ugdgomezdiez.androidtraining.features.ex02.data

import com.google.gson.annotations.SerializedName

data class DogApiModel(
        @SerializedName("name") val name: String,
        @SerializedName("short_description") val short_description: String,
        @SerializedName("sex") val sex: String,
        @SerializedName("date_birth") val date_birth: String,
        @SerializedName("url_image") val url_image: String
)