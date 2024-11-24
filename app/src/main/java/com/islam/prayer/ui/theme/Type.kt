package com.islam.prayer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,  // Larger size for titles
        lineHeight = 40.sp,  // Increased line height
        letterSpacing = 0.25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,  // Increased size for medium titles
        lineHeight = 36.sp,  // Increased line height for better readability
        letterSpacing = 0.2.sp  // Adjusted for the larger font size
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,  // Increased size for small titles
        lineHeight = 32.sp,  // Increased line height for readability
        letterSpacing = 0.15.sp  // Adjusted for the larger font size
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize =  16.sp * 1.8,
        lineHeight =  22.sp * 1.8,
        letterSpacing =  0.25.sp * 1.8
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize =  16.sp * 1.4,
        lineHeight =  22.sp * 1.4,
        letterSpacing =  0.25.sp * 1.4
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize =  16.sp * 1.2,
        lineHeight =  22.sp * 1.2,
        letterSpacing =  0.25.sp * 1.2
    )
)