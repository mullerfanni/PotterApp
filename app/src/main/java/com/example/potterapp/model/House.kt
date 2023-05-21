package com.example.potterapp.model

import androidx.compose.ui.graphics.Color
import com.example.potterapp.theme.Colors
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class House: HouseInterface {
    @SerializedName("Hufflepuff")
    @SerialName("Hufflepuff")
    HUFFLEPUFF {
        override val colors: List<Color>
            get() = listOf(Colors.huff_brown, Colors.huff_yellow)
        override val text: String
            get() = "Hufflepuff"
    },
    @SerializedName("Ravenclaw")
    @SerialName("Ravenclaw")
    RAVENCLAW {
        override val colors: List<Color>
            get() = listOf(Colors.rav_blue, Colors.rav_white)
        override val text: String
            get() = "Ravenclaw"
    },
    @SerializedName("Gryffindor")
    @SerialName("Gryffindor")
    GRYFFINDOR {
        override val colors: List<Color>
            get() = listOf(Colors.grif_red, Colors.grif_gold)
        override val text: String
            get() = "Gryffindor"
    },
    @SerializedName("Slytherin")
    @SerialName("Slytherin")
    SLYTHERIN {
        override val colors: List<Color>
            get() = listOf(Colors.sly_green, Colors.sly_grey)
        override val text: String
            get() = "Slytherin"
    },
    @SerializedName("")
    @SerialName("")
    UNKNOWN{
        override val colors: List<Color>
            get() = listOf(Colors.unk_black, Colors.unk_white)
        override val text: String
            get() = "Unknown"
    }
}

interface HouseInterface {
    val colors: List<Color>
    val text: String
}