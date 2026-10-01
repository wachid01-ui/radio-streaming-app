package com.example.radiostreaming.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.radiostreaming.data.RadioRepository
import com.example.radiostreaming.player.RadioPlayerManager
import com.example.radiostreaming.ui.components.BottomPlayerBar
import com.example.radiostreaming.ui.screens.FavoriteScreen
import com.example.radiostreaming.ui.screens.SearchScreen
import com.example.radiostreaming.ui.screens.SettingScreen
import com.example.radiostreaming.ui.theme.RadioAccent
import com.example.radiostreaming.ui.theme.TextGray

enum class NavigationItem(val label: String, val icon: ImageVector) {
    SEARCH("Search", Icons.Default.Search),
    FAVORITE("Favorit", Icons.Default.Favorite),
    SETTING("Setting", Icons.Default.Settings)
}

@Composable
fun MainApp(
    playerManager: RadioPlayerManager
) {
    var currentTab by remember { mutableStateOf(NavigationItem.SEARCH) }
    val stations = remember { RadioRepository.sampleStations }
    val favoriteIds = remember { mutableStateListOf<String>("1", "4") } // Default favorites

    Scaffold(
        bottomBar = {
            Column {
                // Mini Player jika sedang memutar atau stasiun dipilih
                val currentStation = playerManager.currentStation
                if (currentStation != null) {
                    BottomPlayerBar(
                        station = currentStation,
                        isPlaying = playerManager.isPlaying,
                        isBuffering = playerManager.isBuffering,
                        errorMessage = playerManager.errorMessage,
                        onTogglePlay = { playerManager.togglePlayPause() },
                        onClose = { playerManager.stop() }
                    )
                }

                // 3 Menu Navigasi Utama: Search, Favorit, Setting
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp
                ) {
                    NavigationItem.values().forEach { item ->
                        val selected = currentTab == item
                        NavigationBarItem(
                            selected = selected,
                            onClick = { currentTab = item },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = RadioAccent,
                                selectedTextColor = RadioAccent,
                                indicatorColor = RadioAccent.copy(alpha = 0.2f),
                                unselectedIconColor = TextGray,
                                unselectedTextColor = TextGray
                            )
                        )
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                NavigationItem.SEARCH -> {
                    SearchScreen(
                        stations = stations,
                        playerManager = playerManager,
                        favoriteIds = favoriteIds.toSet(),
                        onToggleFavorite = { id ->
                            if (favoriteIds.contains(id)) {
                                favoriteIds.remove(id)
                            } else {
                                favoriteIds.add(id)
                            }
                        }
                    )
                }
                NavigationItem.FAVORITE -> {
                    FavoriteScreen(
                        allStations = stations,
                        favoriteIds = favoriteIds.toSet(),
                        playerManager = playerManager,
                        onToggleFavorite = { id ->
                            if (favoriteIds.contains(id)) {
                                favoriteIds.remove(id)
                            } else {
                                favoriteIds.add(id)
                            }
                        }
                    )
                }
                NavigationItem.SETTING -> {
                    SettingScreen()
                }
            }
        }
    }
}
