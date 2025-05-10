package com.millie.android.data.dto

import com.google.gson.annotations.SerializedName
import com.millie.android.domain.model.CatImage

data class ImagesSearchDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
)

fun ImagesSearchDto.toDomain(): CatImage {
    return CatImage(
        id = id,
        url = url,
        width = width,
        height = height
    )
}