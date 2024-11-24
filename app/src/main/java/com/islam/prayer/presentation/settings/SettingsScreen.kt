package com.islam.prayer.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.test.simpleapp.presentation.uiutil.bodyMediumStyle
import com.test.simpleapp.presentation.uiutil.surfaceColor

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surfaceColor()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings Screen",
            style = bodyMediumStyle(),
            fontWeight = FontWeight.Bold
        )
    }
}