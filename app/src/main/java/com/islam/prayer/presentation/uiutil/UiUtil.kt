package com.test.simpleapp.presentation.uiutil

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

object UiStrings{
    const val  MY_FAVOURITES = "My Favorites"
    const val PLEASE_CONNECT    = "You're offline. Please connect to the internet to continue."
}
object UiUtil {

    const val SHORT_PLOT="short"
    const val FULL_PLOT="full"

    private val defaultSearches =
        listOf("Superman", "Vampire", "Zombie", "Death", "Mission Impossible", "Game")
    val defaultSearch = defaultSearches.random()

    fun launchYoutube(title: String, context: Context) {
        val intent = Intent(Intent.ACTION_SEARCH).apply {
            setPackage("com.google.android.google.youtube")
            putExtra("query", title)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=$title")
            )
            context.startActivity(browserIntent)
        }
    }

    @Composable
    fun titleStyle(title: String): TextStyle {
        val no = title.noOfWords()
        return when{
            no <4 -> titleMediumStyle()
            no in 4..5 ->bodyLargeStyle()
            else-> bodyMediumStyle()
        }
    }

    fun String.noOfWords(): Int {
        return this
            .trim()
            .split("\\s+".toRegex())
            .size
    }

    fun intRating(ratingStr: String): Int {
        return ratingStr
            .trim()
            .removeSuffix("%")
            .toInt()
    }
}