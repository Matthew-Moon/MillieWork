package com.millie.android.data.repository

import com.millie.android.data.dto.toDomain
import com.millie.android.data.service.CatApiService
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.repository.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val api: CatApiService
) : CatRepository {
    override suspend fun getCatImages(): List<CatImage> {
        val result = api.getImagesSearch(limit = 10)
        return result.map { it.toDomain() }
    }
}