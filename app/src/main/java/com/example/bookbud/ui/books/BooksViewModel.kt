package com.example.bookbud.ui.books

import androidx.compose.runtime.StateFlow
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbud.api.GoogleBooksApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksApi: GoogleBooksApi
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val response = booksApi.getRandomBooks(
                    query = "subject:fiction", // Rastgele kitaplar için
                    maxResults = 20
                )
                _uiState.update { 
                    it.copy(
                        popularBooks = response.items,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun refreshBooks() {
        fetchBooks()
    }
}

data class BooksUiState(
    val popularBooks: List<BookItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)