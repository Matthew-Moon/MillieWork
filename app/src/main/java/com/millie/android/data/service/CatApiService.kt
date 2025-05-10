package com.millie.android.data.service

import com.millie.android.data.model.CatImageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getImagesSearch(
        @Query("limit") limit: Int = 10
    ): List<CatImageDto>
}
