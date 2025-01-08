package com.example.bookbud.ui.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbud.model.Book
import com.example.bookbud.model.Review
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Book
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    bookId: String,
    onBackPress: () -> Unit,
    onSaveBook: (Book) -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val uiState by remember { viewModel.uiState }.collectAsState()
    val book = uiState.book
    var showReviewDialog by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }

    LaunchedEffect(uiState.bookSaved) {
        if (uiState.bookSaved) {
            snackbarHostState.showSnackbar("Kitap kaydedildi")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(uiState.book?.title ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBackPress) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { 
                                uiState.book?.let { onSaveBook(it) }
                            }
                        ) {
                            Icon(
                                imageVector = if (uiState.book?.isSaved == true) 
                                    Icons.Default.Bookmark 
                                else 
                                    Icons.Default.BookmarkBorder,
                                contentDescription = "Save Book"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showReviewDialog = true },
                    containerColor = neonGreen
                ) {
                    Icon(Icons.Default.Add, "Add Review")
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = neonGreen)
                    }
                }
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Bir hata oluştu",
                                color = Color.White
                            )
                            Button(
                                onClick = { viewModel.loadBook(bookId) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = neonGreen
                                )
                            ) {
                                Text("Tekrar Dene")
                            }
                        }
                    }
                }
                else -> {
                    BookDetailContent(
                        book = uiState.book,
                        padding = padding
                    )
                }
            }

            if (showReviewDialog) {
                AddReviewDialog(
                    onDismiss = { showReviewDialog = false },
                    onSubmit = { rating, comment ->
                        viewModel.addReview(rating, comment)
                        showReviewDialog = false
                    }
                )
            }
        }
    }
}

@Composable
private fun BookDetailContent(
    book: Book?,
    padding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        // Book Cover
        item {
            BookCover(book?.coverUrl)
        }

        // Book Info
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = book?.title ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = book?.author ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                RatingBar(
                    rating = book?.rating ?: 0f,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Açıklama",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = book?.description ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        // Reviews Section
        item {
            ReviewsSection(
                reviews = book?.reviews ?: emptyList()
            )
        }
    }
}

@Composable
private fun ReviewsSection(
    reviews: List<Review>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Yorumlar",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        reviews.forEach { review ->
            ReviewItem(review)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = review.userName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = review.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun AddReviewDialog(
    onDismiss: () -> Unit,
    onSubmit: (Float, String) -> Unit
) {
    var rating by remember { mutableStateOf(0f) }
    var comment by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Yorum Ekle") },
        text = {
            Column {
                OutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text("Yorumunuz") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onSubmit(rating, comment) }
            ) {
                Text("Gönder")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}

@Composable
private fun BookCover(coverUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (coverUrl != null) {
            AsyncImage(
                model = coverUrl,
                contentDescription = "Book Cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = Color.White.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
private fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = null,
                tint = neonGreen,
                modifier = Modifier.size(20.dp)
            )
        }
    }
} 