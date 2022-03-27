package com.example.mcclab.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.example.mcclab.adapters.ClothesAdapter
import com.example.mcclab.models.ClothesModel
import com.example.mcclab.models.ElectronicsModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_clothes.*
import kotlinx.android.synthetic.main.activity_food.*

class ClothesActivity : AppCompatActivity() {
    private lateinit var clothesAdapter: ClothesAdapter
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var clothes: ArrayList<ClothesModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        clothes = arrayListOf()
        clothesAdapter = ClothesAdapter(this, clothes)
        rv_clothes.adapter = clothesAdapter
        retrieveClothes()
    }

    private fun retrieveClothes() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("clothes")
            .get().addOnCompleteListener { snap ->
                if (snap != null) {
                    for (result in snap.result.documents) {
                        result.toObject(ClothesModel::class.java)?.let { clothes.add(it) }
                    }
                    clothesAdapter.notifyDataSetChanged()
                }
            }
    }
}
