package com.islam.prayer.domain.rep

interface LDTogglesRep {
    fun isFeatureEnabled(toggleKey:String):Boolean
}