import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbud.R
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Üst kısım için boşluk
            Spacer(modifier = Modifier.height(120.dp))
            
            // Logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(neonGreen, shape = RoundedCornerShape(16.dp))
            )
            
            // App Name
            Text(
                text = "bookbud",
                color = neonGreen,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            // Slogan
            Text(
                text = "Keşfet, Oku, Paylaş",
                color = neonGreen.copy(alpha = 0.7f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Alt kısım için Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Google Sign In Button
            Button(
                onClick = { /* Google Sign In Logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp)  // Corner radius arttırıldı
            ) {
                Text(
                    text = "Continue with Google",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            // Sözleşme Bilgilendirme Metni
            Text(
                text = "Uygulamaya giriş yaparak gizlilik ve kullanıcı sözleşmelerini kabul etmiş sayılırsınız",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Sözleşme Linkleri
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kullanıcı Sözleşmesi",
                    color = neonGreen,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Kullanıcı Sözleşmesi Action */ }
                )
                Text(
                    text = " • ",
                    color = neonGreen,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = "Gizlilik Sözleşmesi",
                    color = neonGreen,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Gizlilik Sözleşmesi Action */ }
                )
            }
            
            // En altta boşluk
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
} 