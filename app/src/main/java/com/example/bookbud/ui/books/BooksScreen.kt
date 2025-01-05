import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookbud.ui.theme.darkGreen

@Composable
fun BooksScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(darkGreen)
    ) {
        item { 
            BookSection("Popüler Kitaplar") 
        }
        item { 
            BookSection("Yeni Çıkanlar") 
        }
        item { 
            BookSection("Klasikler") 
        }
        item { 
            BookSection("Bilim Kurgu") 
        }
    }
}

@Composable
fun BookSection(title: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(10) { index ->
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 200.dp)
                        .padding(end = 8.dp)
                        .background(
                            color = Color(
                                (index * 50) % 256,
                                (index * 30) % 256,
                                (index * 70) % 256
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BooksScreen(PaddingValues())
}

@Preview(showBackground = true)
@Composable
fun BookSectionPreview() {
    BookSection("Preview Section")
} 