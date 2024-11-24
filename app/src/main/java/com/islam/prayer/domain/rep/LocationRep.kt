package com.islam.prayer.domain.rep

import android.location.Location

interface LocationRep {
    suspend fun getCurrentLocation():Location?
}