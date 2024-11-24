package com.islam.prayer.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.islam.prayer.data.repimpl.LocationRepImpl
import com.islam.prayer.domain.rep.LocationRep
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
}