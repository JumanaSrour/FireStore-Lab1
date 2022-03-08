package com.example.mcclab.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.example.mcclab.adapters.ContactAdapter
import com.example.mcclab.models.ContactModel
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var contacts: ArrayList<ContactModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEventListeners()

        contacts = arrayListOf()
        contactAdapter = ContactAdapter(this, contacts)
        rv_contacts.adapter = contactAdapter
        retrieveContacts()
    }

    private fun retrieveContacts() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("contacts")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    try {
                        if (error != null) {
                            Toast.makeText(this@MainActivity, error.message.toString(), Toast.LENGTH_SHORT).show()
                            return
                        } else {
                            for (documentChange: DocumentChange in value?.documentChanges!!) {
                                if (documentChange.type == DocumentChange.Type.ADDED) {
                                    contacts.add(documentChange.document.toObject(ContactModel::class.java))
                                }
                            }
                            contactAdapter.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun setEventListeners() {
        btn_add_contact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}
