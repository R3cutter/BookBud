package com.example.bookbud.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.bookbud.model.Book
import com.example.bookbud.model.Review
import kotlinx.coroutines.flow.update

@HiltViewModel
class BookDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState: StateFlow<BookDetailUiState> = _uiState.asStateFlow()

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Simulate loading book data
                val book = Book(
                    id = bookId,
                    title = "Sample Book",
                    author = "Author Name",
                    coverUrl = "",
                    description = "This is a sample book description.",
                    publishYear = 2023,
                    genre = "Fiction",
                    rating = 4.5f,
                    reviews = listOf(
                        Review(
                            id = "1",
                            userId = "user1",
                            userName = "User One",
                            rating = 5f,
                            comment = "Great book!",
                            date = "2023-10-01"
                        )
                    )
                )
                _uiState.update { 
                    it.copy(
                        book = book,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun addReview(rating: Float, comment: String) {
        viewModelScope.launch {
            try {
                val currentBook = uiState.value.book ?: return@launch
                // Simulate adding a review
                val newReview = Review(
                    id = "2",
                    userId = "user2",
                    userName = "User Two",
                    rating = rating,
                    comment = comment,
                    date = "2023-10-02"
                )
                val updatedReviews = currentBook.reviews + newReview
                _uiState.update { 
                    it.copy(
                        book = currentBook.copy(reviews = updatedReviews)
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(error = e.message ?: "Unknown error")
                }
            }
        }
    }
}

data class BookDetailUiState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) 