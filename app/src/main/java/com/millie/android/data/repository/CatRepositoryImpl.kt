package com.millie.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.millie.android.data.db.CatDao
import com.millie.android.data.mapper.toDomain
import com.millie.android.data.paging.CatPagingSource
import com.millie.android.data.service.CatApiService
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
//            .map { data ->
//                data.map { dto -> dto.toDomain() }
//            }
    }
}