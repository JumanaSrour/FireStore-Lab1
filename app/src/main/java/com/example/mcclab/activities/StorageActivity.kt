package com.example.mcclab.activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.mcclab.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.File
import java.lang.Exception
import java.util.jar.Manifest

class StorageActivity : AppCompatActivity() {
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var parentReference: StorageReference
    private lateinit var childReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        firebaseStorage = FirebaseStorage.getInstance()
        val permissions: Array<String> = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        ActivityCompat.requestPermissions(this, permissions, 1)

        val path = "storage/emulated/0/DCIM/Camera/IMG_2022_0313_085749.jpg"
        val file = File(path)
        val uri = Uri.fromFile(file)

        parentReference = firebaseStorage.getReference()

        val fileName: Long = System.currentTimeMillis() / 1000
        val pathName = "uploads/images/" + fileName
        childReference = parentReference.child(pathName)
        childReference.putFile(uri).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                childReference.downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri> {
                    override fun onSuccess(p0: Uri?) {
                        pb_load.visibility = View.GONE
                        Glide.with(applicationContext).load(uri).into(iv_photo)
                    }
                })
            }
        }).addOnFailureListener(object : OnFailureListener {
            override fun onFailure(p0: Exception) {
                Toast.makeText(this@StorageActivity, p0.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
