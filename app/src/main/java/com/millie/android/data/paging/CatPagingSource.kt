package com.millie.android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.millie.android.data.db.CatDao
import com.millie.android.data.model.CatImageDto
import com.millie.android.data.model.CatImageEntity
import com.millie.android.data.service.CatApiService
import javax.inject.Inject

class CatPagingSource @Inject constructor(
    private val api: CatApiService,
    private val dao: CatDao
) : PagingSource<Int, CatImageDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImageDto> {
        return try {
            val response = api.getImagesSearch()
            val data = response.map { it }

            // TODO 캐싱전략.. 필요해보임 데이터가 있다면 불러와서 쓸것?

            insertAll(data)

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = (params.key ?: 1) + 1
            )

        } catch (e: Exception) {
            val fail = dao.getAll().map {
                CatImageDto(
                    id = it.id,
                    url = it.url,
                    width = it.width,
                    height = it.height
                )
            }

            LoadResult.Page(
                data = fail,
                prevKey = null,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatImageDto>): Int? = 1

    private suspend fun insertAll(data: List<CatImageDto>) {
        dao.insertAll(data.map {
            CatImageEntity(
                id = it.id,
                url = it.url,
                width = it.width,
                height = it.height
            )
        })
    }
}
