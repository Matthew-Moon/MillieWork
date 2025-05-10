package com.millie.android.domain.repository

import androidx.paging.PagingData
import com.millie.android.domain.model.CatImage
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCatImages(): Flow<PagingData<CatImage>>
}