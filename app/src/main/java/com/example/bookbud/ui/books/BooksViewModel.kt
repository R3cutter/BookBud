package com.example.bookbud.ui.books

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.bookbud.model.Book

@HiltViewModel
class BooksViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()
}

data class BooksUiState(
    val popularBooks: List<Book> = emptyList(),
    val newBooks: List<Book> = emptyList(),
    val classicBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) 