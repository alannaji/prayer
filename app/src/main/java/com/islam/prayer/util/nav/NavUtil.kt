package com.test.simpleapp.util.nav

import NEvent
import UiEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

object NavUtil {

    @Composable
    fun <T> ObserveNavEvent(
        flow: Flow<T>,
        onEvent: (T) -> Unit
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(flow, lifecycleOwner.lifecycle) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(onEvent)
            }
        }
    }
    fun processNEvent(
        nEvent: NEvent,
        sendEvent: (UiEvent) -> Unit,
    ){
        /*when(nEvent){
            *//*NEvent.GoToFavouritesScreen -> {
                sendEvent(
                    UiEvent.Navigate(Route.FavouritesRoute)
                )
            }
            NEvent.GoToHomeScreen -> {
                sendEvent(
                    UiEvent.Navigate(Route.MoviesListRoute)
                )
            }
            NEvent.GoToProfileScreen -> TODO()
            NEvent.GoToSettingsScreen -> TODO()*//*
        }*/
    }
}
/*
data class NavEntity(
    val title: String = "",
    val icon: ImageVector,
    val profilePictureUrl:String="",
    val nEvent: (() -> NEvent)?
){
    companion object{
        const val HOME = "Home"
        const val FAVOURITES = "Favourites"
        const val SETTINGS = "Settings"
        const val PROFILE = "Profile"
    }
}

object MainNav {
    fun navEntities(
    ): List<NavEntity> = listOf(
        NavEntity(
            title = HOME,
            icon = Icons.Filled.Home,
            nEvent = {
                NEvent.GoToHomeScreen
            }
        ),
        NavEntity(
            title = FAVOURITES,
            icon = Icons.Filled.Bookmarks,
            nEvent = {
                NEvent.GoToFavouritesScreen
            }
        ),
        NavEntity(
            title = SETTINGS,
            icon = Icons.Filled.Settings,
            nEvent = null
        ),
        NavEntity(
            title = PROFILE,
            icon = Icons.Filled.Person,
            nEvent = null
        )
    )
}
enum class SelectedScreen(val screenId:Int){
    HOME_SCREEN(0),
    FAVOURITES(1);

    companion object{
        fun getScreenById(screenId:Int):SelectedScreen{
            return  when(screenId){
                0-> HOME_SCREEN
                1-> SelectedScreen.FAVOURITES
                else->throw IllegalArgumentException("wrong screen id")
            }
        }
    }
}*/
