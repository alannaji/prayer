package com.islam.prayer.di

import android.app.Application
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.islam.prayer.data.repimpl.LDTogglesRepImpl
import com.islam.prayer.data.repimpl.LocationRepImpl
import com.islam.prayer.domain.rep.LDTogglesRep
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.domain.util.PrayerManager
import com.islam.prayer.domain.util.PrayerManagerFactory
import com.islam.prayer.util.AppConstants.TAG
import com.launchdarkly.logging.LDLogLevel
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(
        app: Application
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(app)

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        app: Application
    ): LocationRep = LocationRepImpl(
        fusedLocationProviderClient,
        app
    )
    @Provides
    @Singleton
    fun providesPrayerManagerFactory(
        locationRep: LocationRep
    ):PrayerManagerFactory = PrayerManagerFactory(locationRep)


    @Provides
    @Singleton
    fun providesLDClient(
        app: Application
    ):LDClient{
        val config = LDConfig
            .Builder(LDConfig.Builder.AutoEnvAttributes.Enabled)
            .mobileKey("mob-c5910b84-759e-4aa0-a7d9-c0b9880698e3")
            .logLevel(LDLogLevel.DEBUG)
            .build()
        val context = LDContext.create("default-context")

        val client = LDClient.init(app, config,context, 5)
        if(!client.isInitialized){
            Log.e(TAG,"LDClient is not initialized")
        }
        return client
    }

    @Provides
    @Singleton
    fun providesLDTogglesRep(ldClient: LDClient):LDTogglesRep {
        return LDTogglesRepImpl(ldClient)
    }
}
