package com.example.bookbud.ui.books

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbud.model.Book
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen
import com.example.bookbud.ui.theme.BookbudTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.bookbud.R
import coil.compose.AsyncImage
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.Icons
import androidx.compose.ui.layout.ContentScale

@Composable
fun BooksScreen(
    padding: PaddingValues,
    onBookClick: (String) -> Unit,
    viewModel: BooksViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState by remember { viewModel.uiState }.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(darkGreen)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { 
                Text(
                    text = "Kitap Ara...", 
                    color = Color.White.copy(alpha = 0.5f)
                ) 
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = darkGreen,
                unfocusedContainerColor = darkGreen,
                focusedBorderColor = neonGreen,
                unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                focusedLeadingIconColor = neonGreen,
                unfocusedLeadingIconColor = Color.White.copy(alpha = 0.5f),
                focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
                unfocusedPlaceholderColor = Color.White.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Kitap Listesi
        LazyColumn(
            state = rememberLazyListState()
        ) {
            // Filtreleme işlemi
            val filteredPopularBooks = uiState.popularBooks.filter { book ->
                book.title.contains(searchQuery, ignoreCase = true) ||
                book.author.contains(searchQuery, ignoreCase = true)
            }
            
            val filteredNewBooks = uiState.newBooks.filter { book ->
                book.title.contains(searchQuery, ignoreCase = true) ||
                book.author.contains(searchQuery, ignoreCase = true)
            }
            
            val filteredClassicBooks = uiState.classicBooks.filter { book ->
                book.title.contains(searchQuery, ignoreCase = true) ||
                book.author.contains(searchQuery, ignoreCase = true)
            }

            // Boş liste kontrolü
            if (searchQuery.isNotEmpty() && 
                filteredPopularBooks.isEmpty() && 
                filteredNewBooks.isEmpty() && 
                filteredClassicBooks.isEmpty()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sonuç bulunamadı",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                // Kategorileri göster
                if (filteredPopularBooks.isNotEmpty()) {
                    item { 
                        BookSection(
                            title = "Popüler Kitaplar",
                            books = filteredPopularBooks,
                            onBookClick = onBookClick
                        )
                    }
                }
                
                if (filteredNewBooks.isNotEmpty()) {
                    item { 
                        BookSection(
                            title = "Yeni Çıkanlar",
                            books = filteredNewBooks,
                            onBookClick = onBookClick
                        )
                    }
                }
                
                if (filteredClassicBooks.isNotEmpty()) {
                    item { 
                        BookSection(
                            title = "Klasikler",
                            books = filteredClassicBooks,
                            onBookClick = onBookClick
                        )
                    }
                }
            }
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
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (book.coverUrl.isNotEmpty()) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = book.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
private fun BooksScreenPreview() {
    BookbudTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = darkGreen
        ) {
            BooksScreen(
                padding = PaddingValues(),
                onBookClick = {}
            )
        }
    }
} 