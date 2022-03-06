package com.example.mcclab.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContactModel {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null
}
