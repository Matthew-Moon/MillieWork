package com.millie.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.millie.android.data.utils.ImageCacheManager
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.usecase.GetImagesSearchUseCase
import com.millie.android.presentation.state.CatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    getImagesSearchUseCase: GetImagesSearchUseCase,
    val imageCacheManager: ImageCacheManager,
) : ViewModel() {

    //    val catData: Flow<PagingData<CatImage>> =
//        getImagesSearchUseCase()
//            .cachedIn(viewModelScope)
    val catData: Flow<PagingData<CatImage>> =
        getImagesSearchUseCase()
            .map { data ->
                data.map { catImage ->
                    saveFile(catImage)
                    catImage
                }
            }
            .cachedIn(viewModelScope)


    // TODO Room에 이 경로를 저장해야 될 것 같음
    private fun saveFile(catImage: CatImage) {
        viewModelScope.launch { imageCacheManager.saveFile(catImage.url, catImage.id) }
    }
}