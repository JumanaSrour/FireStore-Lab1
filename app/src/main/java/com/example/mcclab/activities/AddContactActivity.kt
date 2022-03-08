package com.example.mcclab.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcclab.R
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        back_addContact.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    // in this activity, you add and upload data to the fireStore, in the adapter you get data, in main activity you display
}
