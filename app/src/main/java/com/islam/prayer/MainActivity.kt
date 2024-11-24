package com.islam.prayer

import UiEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islam.prayer.presentation.main.MainScreen
import com.islam.prayer.presentation.settings.SettingsScreen
import com.islam.prayer.ui.theme.PrayerTheme
import com.islam.prayer.util.nav.Route
import com.test.simpleapp.presentation.uiutil.surfaceColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = surfaceColor()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.MainRoute
                    ){
                        composable<Route.MainRoute> {
                            MainScreen(
                                onNavigate = {uiEvent: UiEvent.Navigate ->
                                    navController.navigate(uiEvent.route)
                                }
                            )
                        }
                        composable<Route.SettingsRoute> {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}
