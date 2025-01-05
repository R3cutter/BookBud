import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { MainBottomBar(selectedTab) { selectedTab = it } }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> BooksScreen(paddingValues)
            1 -> ProfileScreen(paddingValues)
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
    MainScreen()
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