package com.millie.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.millie.android.data.utils.ImageCacheManager
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.usecase.GetImagesSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    getImagesSearchUseCase: GetImagesSearchUseCase,
    val imageCacheManager: ImageCacheManager,
) : ViewModel() {

    val catData: Flow<PagingData<CatImage>> =
        getImagesSearchUseCase()
            .map { data ->
                data.map { catImage ->
                    viewModelScope.launch { imageCacheManager.saveFile(catImage.url, catImage.id) }
                    catImage
                }
            }
            .cachedIn(viewModelScope)
}
