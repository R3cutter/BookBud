package com.example.bookbud.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbud.ui.theme.darkGreen
import com.example.bookbud.ui.theme.neonGreen

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(120.dp))
            
            // Logo placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(neonGreen, shape = RoundedCornerShape(16.dp))
            )
            
            Text(
                text = "bookbud",
                color = neonGreen,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            Text(
                text = "Keşfet, Oku, Paylaş",
                color = neonGreen.copy(alpha = 0.7f),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onLoginSuccess() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Continue with Google",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Uygulamaya giriş yaparak gizlilik ve kullanıcı sözleşmelerini kabul etmiş sayılırsınız",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kullanıcı Sözleşmesi",
                    color = neonGreen,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { showTermsDialog = true }
                        .padding(vertical = 8.dp)
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
                    modifier = Modifier
                        .clickable { showPrivacyDialog = true }
                        .padding(vertical = 8.dp)
                )
            }
        }

        if (showTermsDialog) {
            AgreementDialog(
                title = "Kullanıcı Sözleşmesi",
                content = termsText,
                onDismiss = { showTermsDialog = false }
            )
        }

        if (showPrivacyDialog) {
            AgreementDialog(
                title = "Gizlilik Sözleşmesi",
                content = privacyText,
                onDismiss = { showPrivacyDialog = false }
            )
        }
    }
}

@Composable
fun AgreementDialog(
    title: String,
    content: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(content) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Kapat")
            }
        }
    )
}

private const val termsText = """
    Kullanıcı Sözleşmesi
    
    1. Hizmetin Kullanımı
    Bu uygulamayı kullanarak aşağıdaki şartları kabul etmiş olursunuz...
    
    2. Hesap Güvenliği
    Hesabınızın güvenliğinden siz sorumlusunuz...
    
    3. İçerik Politikası
    Paylaşılan içerikler uygun olmalıdır...
    
    [Buraya gerçek sözleşme metni gelecek]
"""

private const val privacyText = """
    Gizlilik Sözleşmesi
    
    1. Veri Toplama
    Uygulamayı kullanırken toplanan veriler...
    
    2. Veri Kullanımı
    Topladığımız verileri nasıl kullanıyoruz...
    
    3. Veri Güvenliği
    Verilerinizi nasıl koruyoruz...
    
    [Buraya gerçek gizlilik sözleşmesi metni gelecek]
"""

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = TODO()
    )
} 