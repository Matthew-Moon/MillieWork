package com.millie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.millie.data.db.CatDao
import com.millie.data.paging.CatPagingSource
import com.millie.data.service.CatApiService
import com.millie.domain.model.CatImage
import com.millie.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val api: CatApiService,
    private val dao: CatDao,
) : CatRepository {
    override fun getCatImages(): Flow<PagingData<CatImage>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CatPagingSource(api, dao) }
        ).flow
    }
}