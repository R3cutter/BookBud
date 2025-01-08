package com.example.bookbud.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookbud.R
import com.example.bookbud.ui.theme.BookbudTheme
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profil Bilgileri
        ProfileHeader()
    }
}

@Composable
private fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profil Fotoğrafı
        Surface(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            color = darkGreen
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Kullanıcı Bilgileri
        Text(
            text = "Kullanıcı Adı",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Text(
            text = "kullanici@email.com",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.7f)
        )
        
        Text(
            text = "Katılma: Mart 2024",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
private fun ProfileScreenPreview() {
    BookbudTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = darkGreen
        ) {
            ProfileScreen(
                paddingValues = PaddingValues()
            )
        }
    }
} 