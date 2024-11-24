package com.test.simpleapp.presentation.uiutil

import android.media.MediaPlayer
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun PlayWav(
    wavResId: Int
) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, wavResId)
    }
    LaunchedEffect(wavResId) {
        mediaPlayer.start()
    }
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }
}

@Composable
fun WithBottomDivider(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    innerPadding: PaddingValues = PaddingValues(top = 8.dp),
    color: Color = onSurfaceColor(),
    thickness: Dp = 1.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
        HorizontalDivider(
            modifier = modifier.padding(innerPadding),
            color = color,
            thickness = thickness
        )
    }
}


@Composable
fun BackNavIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
        )
    }
}

@Composable
fun getStandardCardColors(): CardColors = CardDefaults.cardColors(
    containerColor = surfaceColor(),
    contentColor = Color.Unspecified,
    disabledContainerColor = surfaceVariant()
)

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = surfaceColor(),
    unfocusedContainerColor = surfaceColor(),
    focusedTextColor = onSurfaceColor(),
    unfocusedTextColor = onSurfaceColor(),
    focusedIndicatorColor = surfaceVariant(),
    unfocusedIndicatorColor = surfaceVariant(),
    cursorColor = primaryColor()
)

@Composable
fun menuItemColors() = MenuDefaults.itemColors(
    leadingIconColor = Color.Unspecified,
    textColor = onSurfaceVariant(),
    disabledLeadingIconColor = Color.White,
    trailingIconColor = Color.Unspecified,
    disabledTrailingIconColor = Color.Unspecified,
    disabledTextColor = onSurfaceVariant(),
)

@Composable
fun bottomBarColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = Color.White,
    unselectedIconColor = onSurfaceColor().darken(0.6f),
    selectedTextColor = onSurfaceColor(),
    indicatorColor = surfaceColor()
)

@Composable
fun backgroundColor(): Color = MaterialTheme.colorScheme.background

@Composable
fun onBackgroundColor(): Color = MaterialTheme.colorScheme.onBackground

@Composable
fun surfaceColor(): Color = MaterialTheme.colorScheme.surface

@Composable
fun onSurfaceColor(): Color = MaterialTheme.colorScheme.onSurface

@Composable
fun surfaceVariant(): Color = MaterialTheme.colorScheme.surfaceVariant

@Composable
fun onSurfaceVariant(): Color = MaterialTheme.colorScheme.onSurfaceVariant

@Composable
fun secondaryColor(): Color = MaterialTheme.colorScheme.secondary

@Composable
fun secondaryContainer(): Color = MaterialTheme.colorScheme.secondaryContainer

@Composable
fun onSecondaryContainerColor(): Color = MaterialTheme.colorScheme.onSecondaryContainer

@Composable
fun primaryColor(): Color = MaterialTheme.colorScheme.primary

@Composable
fun onPrimaryColor(): Color = MaterialTheme.colorScheme.onPrimary

@Composable
fun primaryContainerColor(): Color = MaterialTheme.colorScheme.primaryContainer

@Composable
fun onPrimaryContainerColor(): Color = MaterialTheme.colorScheme.onPrimaryContainer

@Composable
fun bodyLargeStyle(): TextStyle = MaterialTheme.typography.bodyLarge

@Composable
fun bodyMediumStyle(): TextStyle = MaterialTheme.typography.bodyMedium

@Composable
fun bodySmallStyle(): TextStyle = MaterialTheme.typography.bodySmall

@Composable
fun titleLargeStyle(): TextStyle = MaterialTheme.typography.titleLarge

@Composable
fun titleMediumStyle(): TextStyle = MaterialTheme.typography.titleMedium
@Composable
fun titleSmallStyle(): TextStyle = MaterialTheme.typography.titleSmall

@Composable
fun shapeRectangle(): Shape = MaterialTheme.shapes.extraSmall

@Composable
fun shapeSmall(): Shape = MaterialTheme.shapes.small

@Composable
fun shapeMedium(): Shape = MaterialTheme.shapes.medium


@Composable
fun shapeLarge(): Shape = MaterialTheme.shapes.large

@Composable
fun BackIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    tint: Color = onSurfaceColor()
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            modifier = Modifier
                .alpha(alpha),
            contentDescription = "BackIcon",
            tint = tint
        )
    }
}

@Composable
fun CloseIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    tint: Color = onSurfaceColor()
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            modifier = Modifier
                .alpha(alpha),
            contentDescription = "Close",
            tint = tint
        )
    }
}

@Composable
fun TestText(
    modifier: Modifier = Modifier,
    text: String = "hello baby"
) {
    Text(
        text = text,
        style = bodyMediumStyle(),
        color = primaryColor()
    )
}

@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun TextColumn(
    text: String,
    textStyle: TextStyle,
) {
    val words = text.split(" ")

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(words) { word ->
            Text(
                text = word,
                fontWeight = FontWeight.Normal,
                style = textStyle
            )
        }
    }
}


@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}

@Composable
fun Dp.dpToPx(): Float {
    val density = LocalDensity.current
    return with(density) {
        this@dpToPx.toPx()
    }
}