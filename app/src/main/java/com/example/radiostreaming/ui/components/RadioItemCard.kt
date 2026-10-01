package com.example.radiostreaming.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radiostreaming.model.RadioStation
import com.example.radiostreaming.ui.theme.RadioAccent
import com.example.radiostreaming.ui.theme.RadioSecondary
import com.example.radiostreaming.ui.theme.TextGray

@Composable
fun RadioItemCard(
    station: RadioStation,
    isPlaying: Boolean,
    isBuffering: Boolean,
    isFavorite: Boolean,
    onPlayClick: () -> Unit,
    onFavoriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onPlayClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Radio dengan background gradasi
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(RadioAccent, RadioSecondary)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isBuffering) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Radio else Icons.Default.Radio,
                        contentDescription = "Radio Icon",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Info Stasiun Radio
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = station.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = RadioAccent.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = station.genre,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = RadioAccent,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = station.frequency,
                        style = MaterialTheme.typography.bodySmall.copy(color = TextGray),
                        fontSize = 11.sp
                    )
                }
            }

            // Tombol Favorit
            IconButton(onClick = onFavoriteToggle) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color(0xFFFF5252) else TextGray
                )
            }

            // Tombol Play / Pause
            FilledIconButton(
                onClick = onPlayClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = if (isPlaying) RadioSecondary else RadioAccent
                ),
                modifier = Modifier.size(42.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = Color.White
                )
            }
        }
    }
}
