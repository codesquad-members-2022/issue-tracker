package com.example.it.issuetracker.presentation

import android.app.Application
import com.example.it.issuetracker.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class IssueTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@IssueTrackerApplication)
            modules(
                networkModule,
                viewModelModule,
                repositoryModule,
                sharedPrefModule,
                datasourceModule
            )
        }
    }
}