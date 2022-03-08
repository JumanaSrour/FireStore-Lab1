package com.example.mcclab.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.ArrayMap
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var phone: String
    private val contactsCollectionRef = Firebase.firestore.collection("contacts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        initiateProperties()
        setEventListeners()
    }

    private fun initiateProperties() {
        loader.visibility = View.INVISIBLE
        name = ed_name.text.toString()
        address = ed_address.text.toString()
        phone = ed_phone.text.toString()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
    }

    private fun setEventListeners() {
        back_addContact.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn_save.setOnClickListener {
            if (ed_name.text.isEmpty() || ed_address.text.isEmpty() || ed_phone.text.isEmpty()) {
                ed_name.error = getString(R.string.name_required)
                ed_address.error = getString(R.string.address_required)
                ed_phone.error = getString(R.string.phone_required)
            } else {
                saveContact()
            }
        }
    }

    private fun saveContact() {
        initiateProperties()
        try {
            loader.visibility = View.VISIBLE
            this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val param = ArrayMap<String, Any>()
            param["name"] = name
            param["address"] = address
            param["phone"] = phone

            contactsCollectionRef.add(param).addOnCompleteListener {
                Toast.makeText(this@AddContactActivity, getString(R.string.success), Toast.LENGTH_SHORT).show()
                loader.visibility = View.INVISIBLE
                startActivity(Intent(this, MainActivity::class.java))
            }
                .addOnFailureListener {
                    Toast.makeText(this@AddContactActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(this@AddContactActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
