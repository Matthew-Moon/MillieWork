package com.millie.android.data.mapper

import com.millie.android.data.model.CatImageDto
import com.millie.android.data.model.CatImageEntity
import com.millie.android.domain.model.CatImage

// Dto → Domain
fun CatImageDto.toDomain(): CatImage =
    CatImage(id = id, url = url, width = width, height = height)

// Entity -> Domain
fun CatImageEntity.toDomain(): CatImage =
    CatImage(id = id, url = url, width = width, height = height)

// Dto -> Entity
fun CatImageDto.toEntity(): CatImageEntity =
    CatImageEntity(id = id, url = url, width = width, height = height)
