package com.example.bookbud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookbud.ui.login.LoginScreen
import com.example.bookbud.ui.login.EmailLoginScreen
import com.example.bookbud.ui.login.RegisterScreen
import com.example.bookbud.ui.main.MainScreen
import com.example.bookbud.ui.book.detail.BookDetailScreen
import com.example.bookbud.ui.saved.SavedBooksScreen
import com.example.bookbud.ui.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object EmailLogin : Screen("email_login")
    object Register : Screen("register")
    object Main : Screen("main")
    object SavedBooks : Screen("saved_books")
    object Profile : Screen("profile")
    
    object BookDetail : Screen("book/{bookId}") {
        fun createRoute(bookId: String) = "book/$bookId"
    }
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Screen.EmailLogin.route) {
            EmailLoginScreen(navController = navController)
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        
        composable(Screen.Main.route) {
            MainScreen(
                onBookClick = { bookId ->
                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                },
                onSavedBooksClick = {
                    navController.navigate(Screen.SavedBooks.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        
        composable(
            route = Screen.BookDetail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
            BookDetailScreen(
                bookId = bookId,
                onBackPress = { navController.popBackStack() },
                onSaveBook = { /* Book saving logic */ }
            )
        }
        
        composable(Screen.SavedBooks.route) {
            SavedBooksScreen(
                onBookClick = { bookId ->
                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                }
            )
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
} 