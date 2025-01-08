package com.example.bookbud.ui.books

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbud.model.Book
import com.example.bookbud.ui.theme.darkGreen

@Composable
fun BooksScreen(
    padding: PaddingValues,
    onBookClick: (String) -> Unit,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(darkGreen)
    ) {
        item { 
            BookSection(
                title = "Popüler Kitaplar",
                books = uiState.popularBooks,
                onBookClick = onBookClick
            )
        }
        item { 
            BookSection(
                title = "Yeni Çıkanlar",
                books = uiState.newBooks,
                onBookClick = onBookClick
            )
        }
        item { 
            BookSection(
                title = "Klasikler",
                books = uiState.classicBooks,
                onBookClick = onBookClick
            )
        }
    }
}

@Composable
private fun BookSection(
    title: String,
    books: List<Book>,
    onBookClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(books) { book ->
                BookItem(
                    book = book,
                    onClick = { onBookClick(book.id) }
                )
            }
        }
    }
}

@Composable
private fun BookItem(
    book: Book,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(200.dp)
            .padding(end = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BooksScreen(PaddingValues(), {})
}

@Preview(showBackground = true)
@Composable
fun BookSectionPreview() {
    BookSection("Preview Section", emptyList(), {})
} 