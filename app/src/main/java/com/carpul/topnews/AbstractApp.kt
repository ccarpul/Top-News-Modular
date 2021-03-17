package com.carpul.topnews

import android.app.Application
import android.util.Log
import androidx.annotation.CallSuper
import com.carpul.base.view.BaseActivity.Companion.TAG

abstract class AbstractApp : Application() {

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Initializing APP (AbstractApp) ")
    }
}