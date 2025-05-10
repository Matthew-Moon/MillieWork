package com.millie.android.domain.usecase

import androidx.paging.PagingData
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesSearchUseCase @Inject constructor(
    private val repository: CatRepository
){
    operator fun invoke(): Flow<PagingData<CatImage>> = repository.getCatImages()
}