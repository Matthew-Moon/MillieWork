package com.millie.android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.millie.android.data.dto.ImagesSearchDto
import com.millie.android.data.service.CatApiService

class CatPagingSource(
    private val api: CatApiService
) : PagingSource<Int, ImagesSearchDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesSearchDto> {
        return try {
            val response = api.getImagesSearch()
            val data = response.map { it }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = (params.key ?: 1) + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImagesSearchDto>): Int? = 1
}
