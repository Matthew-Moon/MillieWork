package com.millie.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millie.android.domain.usecase.GetImagesSearchUseCase
import com.millie.android.presentation.state.CatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    val getImagesSearchUseCase: GetImagesSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CatUiState>(CatUiState.Loading)
    val uiState: StateFlow<CatUiState> = _uiState

    fun getImagesSearch() {
        viewModelScope.launch {
            _uiState.value = CatUiState.Loading
            try {
                val result = getImagesSearchUseCase()
                Timber.d("getImagesSearch result = $result")
                _uiState.value = CatUiState.Success(result)
            } catch (e: Exception) {
                Timber.e("getImagesSearch result = ${e.message}")
            }
        }
    }
}