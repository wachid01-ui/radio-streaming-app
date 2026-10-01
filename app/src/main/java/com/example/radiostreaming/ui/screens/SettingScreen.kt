package com.example.radiostreaming.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radiostreaming.ui.theme.RadioAccent
import com.example.radiostreaming.ui.theme.TextGray

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showRateDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = "Informasi aplikasi dan kebijakan layanan",
            style = MaterialTheme.typography.bodyMedium.copy(color = TextGray),
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column {
                SettingMenuItem(
                    icon = Icons.Default.PrivacyTip,
                    title = "Kebijakan Privasi (Privacy Policy)",
                    subtitle = "Pelajari bagaimana data Anda dikelola",
                    onClick = { showPrivacyDialog = true }
                )

                Divider(color = MaterialTheme.colorScheme.surfaceVariant, thickness = 1.dp)

                SettingMenuItem(
                    icon = Icons.Default.StarRate,
                    title = "Beri Nilai Kami (Rate Us)",
                    subtitle = "Dukung pengembangan aplikasi ini di Play Store",
                    onClick = { showRateDialog = true }
                )

                Divider(color = MaterialTheme.colorScheme.surfaceVariant, thickness = 1.dp)

                SettingMenuItem(
                    icon = Icons.Default.Info,
                    title = "Tentang Aplikasi",
                    subtitle = "Radio Streaming v1.0.0",
                    onClick = {
                        Toast.makeText(context, "Radio Streaming v1.0.0 - Dibuat dengan Jetpack Compose", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

    // Dialog Kebijakan Privasi
    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = {
                Text(
                    text = "Kebijakan Privasi",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 350.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = """
                        1. Informasi yang Dikumpulkan:
                        Aplikasi Radio Streaming ini tidak mengumpulkan data pribadi sensitif seperti identitas, kontak, maupun lokasi spesifik Anda.
                        
                        2. Akses Jaringan:
                        Aplikasi memerlukan izin Internet semata-mata untuk memutar audio streaming dari stasiun radio publik dan memuat metadata siaran.
                        
                        3. Konten Streaming:
                        Seluruh siaran audio merupakan hak cipta masing-masing stasiun radio penyedia siaran (stream provider).
                        
                        4. Keamanan:
                        Kami berkomitmen untuk menjaga keamanan privasi pengguna dan tidak menjual data apa pun kepada pihak ketiga.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showPrivacyDialog = false }) {
                    Text("Tutup", color = RadioAccent)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        )
    }

    // Dialog Rate Us
    if (showRateDialog) {
        var userRating by remember { mutableStateOf(5) }
        AlertDialog(
            onDismissRequest = { showRateDialog = false },
            title = {
                Text("Beri Nilai Aplikasi", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Apakah Anda menyukai aplikasi Radio Streaming ini? Berikan apresiasi bintang Anda!")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (i in 1..5) {
                            IconButton(onClick = { userRating = i }) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star $i",
                                    tint = if (i <= userRating) Color(0xFFFFB300) else TextGray
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showRateDialog = false
                        Toast.makeText(context, "Terima kasih atas penilaian $userRating bintang Anda!", Toast.LENGTH_SHORT).show()
                        openPlayStore(context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RadioAccent)
                ) {
                    Text("Kirim Ulasan")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRateDialog = false }) {
                    Text("Nanti Saja", color = TextGray)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun SettingMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = RadioAccent,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(color = TextGray)
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Next",
            tint = TextGray
        )
    }
}

private fun openPlayStore(context: Context) {
    val packageName = context.packageName
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    } catch (e: Exception) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
        webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(webIntent)
    }
}
