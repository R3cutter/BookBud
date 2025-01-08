package com.example.bookbud.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.bookbud.ui.books.BooksScreen
import com.example.bookbud.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onBookClick: (String) -> Unit,
    onSavedBooksClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookbud") },
                actions = {
                    IconButton(onClick = onSavedBooksClick) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Saved Books"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, "Books") },
                    label = { Text("Books") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> BooksScreen(
                padding = padding,
                onBookClick = onBookClick
            )
            1 -> ProfileScreen(padding = padding)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text("Bookbud", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = darkGreen
        )
    )
}

@Composable
fun MainBottomBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar(
        containerColor = darkGreen
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Books") },
            label = { Text("Books") },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = neonGreen,
                unselectedIconColor = Color.White.copy(alpha = 0.6f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = neonGreen,
                unselectedIconColor = Color.White.copy(alpha = 0.6f)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onBookClick = {}, onSavedBooksClick = {})
}

@Preview(showBackground = true)
@Composable
fun MainTopBarPreview() {
    MainTopBar()
}

@Preview(showBackground = true)
@Composable
fun MainBottomBarPreview() {
    MainBottomBar(selectedTab = 0, onTabSelected = {})
} 