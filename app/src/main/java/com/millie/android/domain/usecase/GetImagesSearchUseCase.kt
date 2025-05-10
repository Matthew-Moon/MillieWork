package com.millie.android.domain.usecase

import com.millie.android.domain.model.CatImage
import com.millie.android.domain.repository.CatRepository
import javax.inject.Inject

class GetImagesSearchUseCase @Inject constructor(
    private val repository: CatRepository
){
    suspend operator fun invoke(): List<CatImage> = repository.getCatImages()
}