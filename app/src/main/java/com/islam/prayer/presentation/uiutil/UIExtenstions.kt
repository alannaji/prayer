package com.test.simpleapp.presentation.uiutil

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.Easing
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp

fun String.countWords():Int{
    return this
        .trim()
        .split("\\s+".toRegex())
        .filter { it.isNotBlank() }
        .size
}
fun String.capitalize():String = this.lowercase().replaceFirstChar { it.uppercaseChar() }
fun Context.launchIntent(
    intent: Intent,
    onFailure:()->Unit = {}
){
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        onFailure()
    }
}

fun TextStyle.scale(factor: Float):TextStyle{
    return this.copy(fontSize = (fontSize.value * factor).sp)
}

fun Color.darken(factor: Float): Color {
    return Color(
        red = this.red * factor,
        green = this.green * factor,
        blue = this.blue * factor,
        alpha = this.alpha
    )
}

fun Easing.transform(from: Float, to: Float, value: Float): Float {
    return transform(((value - from) * (1f / (to - from))).coerceIn(0f, 1f))
}

operator fun PaddingValues.times(value: Float): PaddingValues = PaddingValues(
    top = calculateTopPadding() * value,
    bottom = calculateBottomPadding() * value,
    start = calculateStartPadding(LayoutDirection.Ltr) * value,
    end = calculateEndPadding(LayoutDirection.Ltr) * value
)



