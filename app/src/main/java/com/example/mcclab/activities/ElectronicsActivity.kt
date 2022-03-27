package com.example.mcclab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.example.mcclab.adapters.ElectronicsAdapter
import com.example.mcclab.models.ElectronicsModel
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_electronics.*
import kotlinx.android.synthetic.main.activity_food.*

class ElectronicsActivity : AppCompatActivity() {
    private lateinit var electronicsAdapter: ElectronicsAdapter
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var electronics: ArrayList<ElectronicsModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronics)

        electronics = arrayListOf()
        electronicsAdapter = ElectronicsAdapter(this, electronics)
        rv_electronics.adapter = electronicsAdapter
        retrieveElectronics()
    }

    private fun retrieveElectronics() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("electronics")
            .get().addOnCompleteListener { snap ->
                if (snap != null) {
                    for (result in snap.result.documents) {
                        result.toObject(ElectronicsModel::class.java)?.let { electronics.add(it) }
                    }
                    electronicsAdapter.notifyDataSetChanged()
                }
            }
    }
}
