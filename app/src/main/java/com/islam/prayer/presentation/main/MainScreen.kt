package com.islam.prayer.presentation.main

import UiEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.islam.prayer.presentation.main.state.UiState
import com.islam.prayer.presentation.main.sub.PrayersList
import com.islam.prayer.ui.theme.Red20
import com.test.simpleapp.presentation.uiutil.bodyMediumStyle
import com.test.simpleapp.presentation.uiutil.shapeMedium
import com.test.simpleapp.presentation.uiutil.surfaceColor
import com.test.simpleapp.util.nav.NavUtil.ObserveNavEvent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit = {}
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val locationPermission = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    LaunchedEffect(locationPermission.allPermissionsGranted) {
        mainViewModel.initialize()
    }

    ObserveNavEvent(flow = mainViewModel.uiEvent) { uiEvent ->
        when (uiEvent) {
            is UiEvent.Navigate -> onNavigate(uiEvent)
            else -> {}
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surfaceColor()),
        contentAlignment = Alignment.Center
    ) {
        if (!locationPermission.allPermissionsGranted) {
            EnableLocationPermission(locationPermission)
        } else {
            when (uiState) {
                UiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UiState.Success -> {
                    val mainState = (uiState as UiState.Success).mainState
                    val prayers = mainState.prayers
                    PrayersList(
                        prayers = prayers,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                }
                is UiState.Error -> {
                    val errorMsg = (uiState as UiState.Error).message?:"Oops Something went wrong"
                    Text(
                        text = errorMsg,
                        style = bodyMediumStyle(),
                        color = Red20
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun EnableLocationPermission(locationPermission: MultiplePermissionsState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Location permission is required to proceed.",
            style = bodyMediumStyle(),
            textAlign = TextAlign.Center
        )
        TextButton(
            onClick = { locationPermission.launchMultiplePermissionRequest() },
            modifier = Modifier.wrapContentSize(),
            shape = shapeMedium(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Red20
            )
        ) {
            Text(
                text = "Grant Permission",
                style = bodyMediumStyle(),
            )
        }
    }
}

/*val isDailyQuranEnabled = mainState.isDailyQuranEnabled
if (isDailyQuranEnabled){
    Text(
        text = "Qul howa allah 2a7ad allahu alsamad",
        style = bodyLargeStyle(),
        color = primaryColor(),
        fontWeight = FontWeight.Bold
    )
}*/

/*val location = (uiState as UiState.Success).location
Text(
    text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}",
    style = bodyMediumStyle(),
    fontWeight = FontWeight.Bold
)*/