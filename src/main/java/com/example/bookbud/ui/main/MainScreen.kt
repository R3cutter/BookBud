package com.example.bookbud.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookbud.ui.books.BooksScreen
import com.example.bookbud.ui.profile.ProfileScreen
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen
import com.example.bookbud.ui.theme.BookbudTheme
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.res.painterResource
import com.example.bookbud.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onBookClick: (String) -> Unit,
    onSavedBooksClick: () -> Unit
) {
    val selectedTab = remember { mutableIntStateOf(0) }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { 
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_icon),
                                contentDescription = "BookBud Logo",
                                modifier = Modifier.size(32.dp)
                            )
                            Text("BookBud")
                        }
                    },
                    actions = {
                        IconButton(onClick = onSavedBooksClick) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Saved Books"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = darkGreen,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = darkGreen
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, "Books") },
                        label = { Text("Books") },
                        selected = selectedTab.intValue == 0,
                        onClick = { selectedTab.intValue = 0 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = neonGreen,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            selectedTextColor = neonGreen,
                            unselectedTextColor = Color.White.copy(alpha = 0.6f)
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, "Profile") },
                        label = { Text("Profile") },
                        selected = selectedTab.intValue == 1,
                        onClick = { selectedTab.intValue = 1 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = neonGreen,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            selectedTextColor = neonGreen,
                            unselectedTextColor = Color.White.copy(alpha = 0.6f)
                        )
                    )
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                when (selectedTab.intValue) {
                    0 -> BooksScreen(
                        padding = paddingValues,
                        onBookClick = remember(onBookClick) { onBookClick }
                    )
                    1 -> ProfileScreen(paddingValues = paddingValues)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
private fun MainScreenPreview() {
    BookbudTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                onBookClick = {},
                onSavedBooksClick = {}
            )
        }
    }
} 