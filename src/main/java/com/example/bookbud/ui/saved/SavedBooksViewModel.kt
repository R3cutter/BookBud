package com.example.bookbud.ui.saved

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.bookbud.model.Book

@HiltViewModel
class SavedBooksViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SavedBooksUiState())
    val uiState: StateFlow<SavedBooksUiState> = _uiState.asStateFlow()
}

data class SavedBooksUiState(
    val savedBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) 