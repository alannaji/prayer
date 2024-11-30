package com.islam.prayer.data.repimpl

import android.util.Log
import com.islam.prayer.domain.rep.LDTogglesRep
import com.launchdarkly.sdk.android.LDClient

class LDTogglesRepImpl(
   private val ldClient: LDClient
):LDTogglesRep {

    override fun isFeatureEnabled(toggleKey: String): Boolean {

        if (!ldClient.isInitialized) {
            Log.e("alaa", "In repImpl")
            Log.e("alaa", "LDClient is not initialized yet")
            return false // Fallback if not ready
        }
        return ldClient.boolVariation(toggleKey,false)
    }
}