package com.example.mcclab.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mcclab.R
import com.example.mcclab.models.ElectronicsModel
import com.example.mcclab.models.FoodModel
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.electronics_item.view.*
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter(var context: Context, food: ArrayList<FoodModel>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    var foodie: List<FoodModel> = food
    private lateinit var analytics: FirebaseAnalytics

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.tv_name_food
        var tvPrice = itemView.tv_price_food
        var cardView = itemView.cv_food
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        analytics = FirebaseAnalytics.getInstance(context)
        val bundle = Bundle()
        val foodItem: FoodModel = foodie[position]
        holder.tvName.text = foodItem.name
        holder.tvPrice.text = foodItem.price.toString()
        holder.cardView.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: card clicked")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, foodItem.name)
            bundle.putInt(FirebaseAnalytics.Param.PRICE, foodItem.price!!)
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        }
    }

    override fun getItemCount(): Int {
        return foodie.size
    }
}