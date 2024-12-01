package com.islam.prayer.presentation.main.state

import android.location.Location
import com.islam.prayer.domain.model.Prayer

data class MainState(
    val prayers:List<Prayer> = emptyList()
)
