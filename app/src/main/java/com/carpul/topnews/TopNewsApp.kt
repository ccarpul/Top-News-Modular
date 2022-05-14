package com.carpul.topnews

import android.app.Application
import androidx.annotation.CallSuper
import com.carpul.news.di.everythingNewsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TopNewsApp : Application() {

    companion object {
        lateinit var mApp: TopNewsApp
            private set
    }
    @CallSuper
    override fun onCreate() {
        super.onCreate()

        mApp = this
        startKoin {
            androidContext(mApp)
            modules(listOf(everythingNewsModule))
        }
    }
}