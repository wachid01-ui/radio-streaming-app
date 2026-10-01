package com.example.radiostreaming.data

import com.example.radiostreaming.model.RadioStation

object RadioRepository {
    val sampleStations = listOf(
        RadioStation(
            id = "1",
            name = "Lofi Chill Radio",
            genre = "Chill / Lo-Fi",
            streamUrl = "https://stream.zeno.fm/f3wvbbqmdg8uv",
            frequency = "Web Stream",
            description = "Musik lo-fi santai untuk menemani belajar dan bekerja."
        ),
        RadioStation(
            id = "2",
            name = "Classic Rock 80s & 90s",
            genre = "Rock",
            streamUrl = "https://stream.zeno.fm/ynxf634280duv",
            frequency = "104.5 FM",
            description = "Lagu-lagu rock klasik terbaik sepanjang masa."
        ),
        RadioStation(
            id = "3",
            name = "Smooth Jazz Lounge",
            genre = "Jazz",
            streamUrl = "https://stream.zeno.fm/0r0xa792kwzuv",
            frequency = "98.2 FM",
            description = "Alunan jazz lembut yang menenangkan pikiran."
        ),
        RadioStation(
            id = "4",
            name = "Hits Pop Indonesia",
            genre = "Pop Indo",
            streamUrl = "https://stream.zeno.fm/u7m459mzg8uv",
            frequency = "101.1 FM",
            description = "Lagu-lagu pop hits teratas Indonesia saat ini."
        ),
        RadioStation(
            id = "5",
            name = "Dangdut Koplo Nusantara",
            genre = "Dangdut",
            streamUrl = "https://stream.zeno.fm/k2k1w2q1bg8uv",
            frequency = "92.4 FM",
            description = "Irama dangdut koplo asik dan penuh semangat."
        ),
        RadioStation(
            id = "6",
            name = "EDM Dance Party",
            genre = "Electronic",
            streamUrl = "https://stream.zeno.fm/3h6k059zg8uv",
            frequency = "107.9 FM",
            description = "Nonstop musik dansa elektronik dan remix terkini."
        ),
        RadioStation(
            id = "7",
            name = "Global News Radio",
            genre = "News & Talk",
            streamUrl = "https://stream.zeno.fm/9k9s709zg8uv",
            frequency = "AM 890",
            description = "Berita terkini, opini, dan wawasan dunia terpercaya."
        )
    )
}
