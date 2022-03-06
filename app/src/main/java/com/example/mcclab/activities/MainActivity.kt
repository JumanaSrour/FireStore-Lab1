package com.example.mcclab.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import com.example.mcclab.adapters.ContactAdapter
import com.example.mcclab.models.ContactModel
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var productAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<ContactModel>()
        data.add(ContactModel())
        data.add(ContactModel())
        data.add(ContactModel())
        data.add(ContactModel())

        productAdapter = ContactAdapter(this, data)
        rv_contacts.adapter = productAdapter

        btn_add_contact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}
