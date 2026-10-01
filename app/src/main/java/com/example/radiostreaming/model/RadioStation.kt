package com.example.radiostreaming.model

data class RadioStation(
    val id: String,
    val name: String,
    val genre: String,
    val streamUrl: String,
    val frequency: String = "Online",
    val description: String = ""
)
