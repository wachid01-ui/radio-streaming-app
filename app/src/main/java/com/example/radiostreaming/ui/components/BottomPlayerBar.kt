package com.example.radiostreaming.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
fun BottomPlayerBar(
    station: RadioStation,
    isPlaying: Boolean,
    isBuffering: Boolean,
    errorMessage: String?,
    onTogglePlay: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 8.dp,
        shadowElevation = 8.dp
    ) {
        Column {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Audio visualizer icon with gradient
                Box(
                    modifier = Modifier
                        .size(44.dp)
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
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.GraphicEq,
                            contentDescription = "Equalizer",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Station info
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = station.name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (isBuffering) "Memuat streaming..." else if (isPlaying) "Sedang Siaran Langsung" else "Dijeda",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (isPlaying) RadioSecondary else TextGray
                        ),
                        fontSize = 11.sp
                    )
                }

                // Play/Pause button
                IconButton(onClick = onTogglePlay) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause",
                        tint = RadioAccent,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Close / Stop button
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Tutup Player",
                        tint = TextGray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
