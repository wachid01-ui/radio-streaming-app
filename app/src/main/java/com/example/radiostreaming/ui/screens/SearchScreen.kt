package com.example.radiostreaming.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radiostreaming.model.RadioStation
import com.example.radiostreaming.player.RadioPlayerManager
import com.example.radiostreaming.ui.components.RadioItemCard
import com.example.radiostreaming.ui.theme.RadioAccent
import com.example.radiostreaming.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    stations: List<RadioStation>,
    playerManager: RadioPlayerManager,
    favoriteIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf("Semua") }

    val genres = remember(stations) {
        listOf("Semua") + stations.map { it.genre }.distinct()
    }

    val filteredStations = remember(searchQuery, selectedGenre, stations) {
        stations.filter { station ->
            val matchesQuery = station.name.contains(searchQuery, ignoreCase = true) ||
                    station.genre.contains(searchQuery, ignoreCase = true) ||
                    station.description.contains(searchQuery, ignoreCase = true)
            val matchesGenre = selectedGenre == "Semua" || station.genre.equals(selectedGenre, ignoreCase = true)
            matchesQuery && matchesGenre
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Eksplorasi Radio",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = "Temukan dan dengarkan siaran radio favorit Anda",
            style = MaterialTheme.typography.bodyMedium.copy(color = TextGray),
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Cari stasiun radio, genre...", color = TextGray) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = RadioAccent)
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Hapus teks", tint = TextGray)
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RadioAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Genre filter chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                val isSelected = selectedGenre == genre
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedGenre = genre },
                    label = { Text(genre) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = RadioAccent,
                        selectedLabelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = TextGray
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Station List
        if (filteredStations.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada stasiun radio yang cocok.",
                    color = TextGray,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredStations, key = { it.id }) { station ->
                    val isCurrentStation = playerManager.currentStation?.id == station.id
                    RadioItemCard(
                        station = station,
                        isPlaying = isCurrentStation && playerManager.isPlaying,
                        isBuffering = isCurrentStation && playerManager.isBuffering,
                        isFavorite = favoriteIds.contains(station.id),
                        onPlayClick = { playerManager.play(station) },
                        onFavoriteToggle = { onToggleFavorite(station.id) }
                    )
                }
            }
        }
    }
}
