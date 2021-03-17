package com.carpul.base.routing

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

interface FeatureRouter {

    abstract class FeatureBundleData {
        abstract fun createBundle(): Bundle
    }


    sealed class Feature {
        object News : Feature()

        open fun createBundleData(): FeatureBundleData? = null
    }

    fun getFeatureIntent(
        context: Context?,
        feature: Feature,
        clearBackStack : Boolean,
        data: Uri?
    ): Intent?
}