package com.example.radiostreaming.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radiostreaming.model.RadioStation
import com.example.radiostreaming.player.RadioPlayerManager
import com.example.radiostreaming.ui.components.RadioItemCard
import com.example.radiostreaming.ui.theme.RadioAccent
import com.example.radiostreaming.ui.theme.TextGray

@Composable
fun FavoriteScreen(
    allStations: List<RadioStation>,
    favoriteIds: Set<String>,
    playerManager: RadioPlayerManager,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteStations = allStations.filter { favoriteIds.contains(it.id) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Radio Favorit",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = "Daftar stasiun radio pilihan yang Anda simpan",
            style = MaterialTheme.typography.bodyMedium.copy(color = TextGray),
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteStations.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Empty Favorites",
                        tint = TextGray.copy(alpha = 0.6f),
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Belum Ada Favorit",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tekan ikon hati pada stasiun radio di menu Search untuk menyimpannya di sini.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextGray),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(favoriteStations, key = { it.id }) { station ->
                    val isCurrentStation = playerManager.currentStation?.id == station.id
                    RadioItemCard(
                        station = station,
                        isPlaying = isCurrentStation && playerManager.isPlaying,
                        isBuffering = isCurrentStation && playerManager.isBuffering,
                        isFavorite = true,
                        onPlayClick = { playerManager.play(station) },
                        onFavoriteToggle = { onToggleFavorite(station.id) }
                    )
                }
            }
        }
    }
}
