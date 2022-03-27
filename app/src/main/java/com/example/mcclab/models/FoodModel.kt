package com.example.mcclab.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodModel{
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null
}
