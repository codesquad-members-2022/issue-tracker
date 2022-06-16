package com.example.it.issuetracker.presentation

import android.app.Application
import com.example.it.issuetracker.di.networkModule
import com.example.it.issuetracker.di.repositoryModule
import com.example.it.issuetracker.di.sharedPrefModule
import com.example.it.issuetracker.di.viewModelModule
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
                sharedPrefModule
            )
        }
    }
}