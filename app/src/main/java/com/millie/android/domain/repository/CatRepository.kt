package com.millie.android.domain.repository

import com.millie.android.domain.model.CatImage

interface CatRepository {
    suspend fun getCatImages(): List<CatImage>
}