package com.sampleandroidarchitecture

import android.app.Application
import com.sampleandroidarchitecture.di.TimberKoinLogger
import com.sampleandroidarchitecture.di.apiModule
import com.sampleandroidarchitecture.di.appModule
import com.sampleandroidarchitecture.di.networkModule
import com.sampleandroidarchitecture.di.repositoryModule
import com.sampleandroidarchitecture.di.repositoryModuleMocked
import com.sampleandroidarchitecture.di.viewModelModule
import com.sampleandroidarchitecture.helper.testMode
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


class App: Application() {

    companion object {
        lateinit var appContext: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        AndroidThreeTen.init(this);
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            logger(
                TimberKoinLogger(
                    if (BuildConfig.DEBUG) Level.DEBUG
                    else Level.ERROR
                )
            )
            modules(
                apiModule,
                appModule,
                networkModule,
                if(testMode) repositoryModuleMocked else repositoryModule,
                viewModelModule
            )
        }
    }
}