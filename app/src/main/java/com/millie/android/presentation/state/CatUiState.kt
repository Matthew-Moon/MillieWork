package com.millie.android.presentation.state

import com.millie.android.domain.model.CatImage

sealed class CatUiState {
    object Loading : CatUiState()
    data class Success(val list: List<CatImage>) : CatUiState()
    data class Error(val message: String) : CatUiState()
}
