package com.example.mcclab.activities

import android.content.Intent
import android.graphics.Color
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

        var electricCounter =0
        btn_electrics.setOnClickListener {
            UITrackingNew("Electronics_Activity", "electronics")
            if (electricCounter < 5) {
                electricCounter++
                if (electricCounter == 5) {
                    userInterests("interest_electronics")
                    startActivity(Intent(this, ElectronicsActivity::class.java))
                }
            }
        }

        var clothCounter = 0
        btn_clothes.setOnClickListener {
            UITrackingNew("Clothes_Activity", "clothes")
            if(clothCounter <5) {
                clothCounter++
                if (clothCounter == 5) {
                    userInterests("interest_clothes")
                    startActivity(Intent(this, ClothesActivity::class.java))
                }
            }
        }

        var foodCounter = 0
        btn_food.setOnClickListener {
            UITrackingNew("Food_Activity", "food")
            if (foodCounter < 5) {
                foodCounter++
                if (foodCounter == 5) {
                    userInterests("interest_food")
                    startActivity(Intent(this, FoodActivity::class.java))
                }
            }
        }
    }

    private fun uiTracking() {
        analytics.setCurrentScreen(this, "third_ui", null)
    }

    private fun UITrackingNew(screenName: String, screenClass: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    private fun userInterests(category: String) {
        analytics.setUserProperty("interests_category", category)
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
