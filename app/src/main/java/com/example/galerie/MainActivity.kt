package com.example.galerie

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // List of artworks
    private val artworks = listOf(
        Artwork(R.drawable.artwork1, "Mona Lisa", "Leonardo da Vinci (1519)"),
        Artwork(R.drawable.artwork2, "The Girl with a Pearl Earring", "Johannes Vermeer (1675)"),
        Artwork(R.drawable.artwork3, "The Scream", "Edvard Munch (1893)")
    )

    // Track the current artwork index
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val artworkImageView: ImageView = findViewById(R.id.artworkImageView)
        val artworkTitleTextView: TextView = findViewById(R.id.artworkTitleTextView)
        val artworkArtistTextView: TextView = findViewById(R.id.artworkArtistTextView)
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)


        updateArtwork(artworkImageView, artworkTitleTextView, artworkArtistTextView)

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                // Swipe left for previous and swipe right for next
                if (velocityX > 0) {
                    showPreviousArtwork()
                } else {
                    showNextArtwork()
                }
                updateArtwork(artworkImageView, artworkTitleTextView, artworkArtistTextView)
                return true
            }
        })


        val touchListener = View.OnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
        findViewById<View>(R.id.constraintLayout).setOnTouchListener(touchListener)


        previousButton.setOnClickListener {
            showPreviousArtwork()
            updateArtwork(artworkImageView, artworkTitleTextView, artworkArtistTextView)
        }

        nextButton.setOnClickListener {
            showNextArtwork()
            updateArtwork(artworkImageView, artworkTitleTextView, artworkArtistTextView)
        }
    }

    // Function to show the previous artwork
    private fun showPreviousArtwork() {
        currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
    }

    // Function to show the next artwork
    private fun showNextArtwork() {
        currentIndex = if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
    }

    // Function to update the displayed artwork
    private fun updateArtwork(imageView: ImageView, titleView: TextView, artistView: TextView) {
        val artwork = artworks[currentIndex]
        imageView.setImageResource(artwork.imageResId)
        titleView.text = artwork.title
        artistView.text = artwork.artist
    }
}

// Artwork data class
data class Artwork(val imageResId: Int, val title: String, val artist: String)
