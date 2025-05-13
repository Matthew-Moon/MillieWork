package com.millie.domain.usecase

import androidx.paging.PagingData
import com.millie.domain.model.CatImage
import com.millie.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesSearchUseCase @Inject constructor(
    private val repository: CatRepository
){
    operator fun invoke(): Flow<PagingData<CatImage>> = repository.getCatImages()
}