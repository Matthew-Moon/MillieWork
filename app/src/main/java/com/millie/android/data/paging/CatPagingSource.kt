package com.millie.android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.millie.android.data.db.CatDao
import com.millie.android.data.mapper.toDomain
import com.millie.android.data.mapper.toEntity
import com.millie.android.data.service.CatApiService
import com.millie.android.domain.model.CatImage
import timber.log.Timber
import javax.inject.Inject

class CatPagingSource @Inject constructor(
    private val api: CatApiService,
    private val dao: CatDao,
) : PagingSource<Int, CatImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImage> {
        return try {
            val response = api.getImagesSearch()
            val data = response.map { it }

            dao.insertAll(data.map { it.toEntity() })
            LoadResult.Page(
                data = data.map { it.toDomain() },
                prevKey = null,
                nextKey = (params.key ?: 1) + 1
            )

        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Page(
                data = dao.getAll().map { it.toDomain() },
                prevKey = null,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? = 1
}
