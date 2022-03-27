package com.example.mcclab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.example.mcclab.adapters.FoodAdapter
import com.example.mcclab.models.ElectronicsModel
import com.example.mcclab.models.FoodModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.android.synthetic.main.activity_main.*

class FoodActivity : AppCompatActivity() {
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var food: ArrayList<FoodModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        food = arrayListOf()
        foodAdapter = FoodAdapter(this, food)
        rv_food.adapter = foodAdapter
        retrieveFood()
    }

    private fun retrieveFood() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("food")
            .get().addOnCompleteListener { snap ->
                if (snap != null) {
                    for (result in snap.result.documents) {
                        result.toObject(FoodModel::class.java)?.let { food.add(it) }
                    }
                    foodAdapter.notifyDataSetChanged()
                }
            }
    }
}

