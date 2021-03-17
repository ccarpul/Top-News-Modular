package com.carpul.base.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TopNewsApp"
    }


    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: BaseActivity")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i(TAG, "onBackPressed: BaseActivity")
        //TODO Custom
    }
}