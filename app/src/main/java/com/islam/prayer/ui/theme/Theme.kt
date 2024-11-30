package com.islam.prayer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFDA0C0C),
    secondary = Color(0x8DE60A05),
    tertiary = Color(0xFFB71C1C),
    background = Color(0xFF000000),
    surface = Color(0xFF000000),
    surfaceVariant = Color(0xFF161616),
    onSurfaceVariant = Color(0xF3FFFBFE),
    onSurface = Color(0xFFFFFBFE),
    onBackground = Color(0xFFFFFBFE) ,
    onPrimary = Color(0xFFFFFBFE),
    onSecondary = Color(0xFFFFFBFE),
    onTertiary = Color(0xFFFFFBFE),
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Red,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF050004),
    surface = Color(0xFF050004),
    surfaceVariant = Color(0xFF1A1919),
    onSurfaceVariant = Color(0xBFFFFBFE),
    onSurface = Color.White ,
    onBackground = Color(0xFFFFFBFE) ,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    )


@Composable
fun PrayerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}