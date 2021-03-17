package com.carpul.topnews

import android.util.Log
import com.carpul.base.view.BaseActivity.Companion.TAG


/**All settings in environment debug
 * Extend similar functionality from the rest of environment (AbstractApp)
 */
class App: AbstractApp() {

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: App debug")
    }
}