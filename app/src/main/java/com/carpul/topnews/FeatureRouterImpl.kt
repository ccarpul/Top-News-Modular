package com.carpul.topnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.carpul.base.routing.FeatureRouter
import com.carpul.news.views.TopNewsActivity

class FeatureRouterImpl : FeatureRouter {

    override fun getFeatureIntent(
        context: Context?,
        feature: FeatureRouter.Feature,
        clearBackStack: Boolean,
        data: Uri?
    ): Intent? {

        context?.let {
            val cls: Class<*> = when (feature) {
                FeatureRouter.Feature.News -> TopNewsActivity::class.java
            }


            val intent = Intent(it, cls)
            intent.data = data
            feature.createBundleData()?.let { data ->
                intent.putExtras(data.createBundle())
            }

            if(clearBackStack) intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            return intent
        }
        return null
    }
}