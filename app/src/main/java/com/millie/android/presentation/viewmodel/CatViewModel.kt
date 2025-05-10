package com.millie.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.millie.android.domain.model.CatImage
import com.millie.android.domain.usecase.GetImagesSearchUseCase
import com.millie.android.presentation.state.CatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    val getImagesSearchUseCase: GetImagesSearchUseCase
) : ViewModel() {

    val catData: Flow<PagingData<CatImage>> =
        getImagesSearchUseCase()
            .cachedIn(viewModelScope)

}