package com.example.galerie

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaring views and variables
    private lateinit var artworkImageView: ImageView
    private lateinit var artworkTitleTextView: TextView
    private lateinit var artworkArtistTextView: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private val artworks = listOf(
        Artwork(R.drawable.artwork1, "Mona Lisa", "Leonardo da Vinci' (1519)"),
        Artwork(R.drawable.artwork2, "The Girl with a Pearl Earring ", "Johannes Vermeer (1675)"),
        Artwork(R.drawable.artwork3, "The Scream", "Edvard Munch (1893)")
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing views
        artworkImageView = findViewById(R.id.artworkImageView)
        artworkTitleTextView = findViewById(R.id.artworkTitleTextView)
        artworkArtistTextView = findViewById(R.id.artworkArtistTextView)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)

        // Update the displayed artwork
        updateArtwork()

        // Set listeners for buttons
        previousButton.setOnClickListener {
            currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
            updateArtwork()
        }

        nextButton.setOnClickListener {
            currentIndex = if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
            updateArtwork()
        }
    }

    // Function to update artwork details
    private fun updateArtwork() {
        val artwork = artworks[currentIndex]
        artworkImageView.setImageResource(artwork.imageResId)
        artworkTitleTextView.text = artwork.title
        artworkArtistTextView.text = artwork.artist
    }
}

// Data class to store artwork details
data class Artwork(val imageResId: Int, val title: String, val artist: String)
