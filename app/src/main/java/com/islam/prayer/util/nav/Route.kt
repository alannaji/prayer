package com.islam.prayer.util.nav

import kotlinx.serialization.Serializable

sealed class Route{

    @Serializable
    data object MainRoute:Route()

    @Serializable
    data object SettingsRoute:Route()
}