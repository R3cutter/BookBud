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
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookbud.viewmodel.AuthViewModel
import android.app.Activity
import com.example.bookbud.R
import com.example.bookbud.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { token ->
                    viewModel.signInWithGoogle(token)
                }
            } catch (e: ApiException) {
                // Hata işleme
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Diğer UI elemanları...

        // Giriş Yap Butonu
        Button(
            onClick = { navController.navigate(Screen.EmailLogin.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "E-posta ile Giriş Yap")
        }

        // Kayıt Ol Butonu
        Button(
            onClick = { navController.navigate(Screen.Register.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Kayıt Ol")
        }

        // Google ile Giriş Butonu (mevcut)
        Button(
            onClick = { 
                launcher.launch(googleSignInClient.signInIntent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Google ile Giriş Yap")
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
        navController = TODO()
    )
} 