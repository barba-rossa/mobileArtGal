// MainActivity.kt
package com.example.myartgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp() {
    val artworks = listOf(
        Artwork(
            R.drawable.artwork1,
            "World of Warcraft Elf",
            "Blizzard",
            "2010"
        ),
        Artwork(
            R.drawable.artwork2,
            "Night Elf - Paladin",
            "Blizzard",
            "2010"
        ),
        Artwork(
            R.drawable.artwork3,
            "Night Elf - Knight",
            "Blizzard",
            "2010"
        )
    )

    var currentArtworkIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Artwork Display
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = artworks[currentArtworkIndex].imageResourceId),
                contentDescription = artworks[currentArtworkIndex].title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        // Artwork Information
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = artworks[currentArtworkIndex].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = artworks[currentArtworkIndex].artist,
                fontSize = 18.sp
            )
            Text(
                text = artworks[currentArtworkIndex].year,
                fontSize = 16.sp
            )
        }

        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentArtworkIndex = if (currentArtworkIndex == 0) {
                        artworks.size - 1
                    } else {
                        currentArtworkIndex - 1
                    }
                }
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    currentArtworkIndex = if (currentArtworkIndex == artworks.size - 1) {
                        0
                    } else {
                        currentArtworkIndex + 1
                    }
                }
            ) {
                Text("Next")
            }
        }
    }
}