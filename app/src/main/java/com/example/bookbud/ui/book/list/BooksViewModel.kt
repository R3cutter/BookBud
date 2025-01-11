package com.example.bookbud.ui.book.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbud.api.GoogleBooksApi
import com.example.bookbud.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BooksUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val popularBooks: List<Book> = emptyList()
)

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksApi: GoogleBooksApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    init {
        refreshBooks()
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                val response = booksApi.getRandomBooks("fiction", 20)
                val books = response.items.map { bookItem ->
                    Book(
                        id = bookItem.id,
                        title = bookItem.volumeInfo.title,
                        authors = bookItem.volumeInfo.authors ?: emptyList(),
                        description = bookItem.volumeInfo.description ?: "",
                        imageUrl = bookItem.volumeInfo.imageLinks?.thumbnail,
                        isSaved = false,
                        reviews = emptyList()
                    )
                }
                _uiState.update { it.copy(
                    isLoading = false,
                    error = null,
                    popularBooks = books
                ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                ) }
            }
        }
    }
} 