package com.millie.domain.repository

import androidx.paging.PagingData
import com.millie.domain.model.CatImage
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCatImages(): Flow<PagingData<CatImage>>
}