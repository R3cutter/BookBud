package com.example.bookbud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookbud.ui.book.list.BooksScreen
import com.example.bookbud.ui.theme.BookBudTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookBudTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = "books"
                    ) {
                        composable("books") {
                            BooksScreen(
                                paddingValues = it.arguments?.let { _ -> androidx.compose.foundation.layout.PaddingValues() }
                                    ?: androidx.compose.foundation.layout.PaddingValues(),
                                onBookClick = { bookId ->
                                    navController.navigate("book_details/$bookId")
                                }
                            )
                        }
                        // Diğer ekranlar için composable'lar buraya eklenecek
                    }
                }
            }
        }
    }
}