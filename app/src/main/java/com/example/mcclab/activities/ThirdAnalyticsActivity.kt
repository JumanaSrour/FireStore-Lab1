package com.example.mcclab.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import kotlinx.android.synthetic.main.activity_third_analytics.*

class ThirdAnalyticsActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_analytics)
        analytics = FirebaseAnalytics.getInstance(this)

        btn_electrics.setOnClickListener {
            startActivity(Intent(this, ElectronicsActivity::class.java))
        }
        btn_clothes.setOnClickListener {
            startActivity(Intent(this, ClothesActivity::class.java))
        }
        btn_food.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
    }

    private fun uiTracking() {
        analytics.setCurrentScreen(this, "third_ui", null)
    }

    private fun UITrackingNew() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ThirdAnalyticsActivity")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "main activity")
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    private fun userInterests(color: String) {
        analytics.setUserProperty("interests_color", color)
    }
    private fun selectEventLog() {
        val bundle: Bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "123456")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "burger")
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Fast Food")

        analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
    }
    private fun shareImage() {
        shareEventLog("flowers", "whats app")
    }
    private fun shareEventLog(img_name: String, shareTo: String) {
        val bundle: Bundle = Bundle()
        bundle.putString("img_name", img_name)
        bundle.putString("shareTo", shareTo)
        analytics.logEvent("shareTo", bundle)
    }
}
