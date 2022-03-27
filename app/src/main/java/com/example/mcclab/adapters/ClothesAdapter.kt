package com.example.mcclab.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mcclab.R
import com.example.mcclab.models.ClothesModel
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.clothes_item.view.*
import kotlinx.android.synthetic.main.contact_item.view.*

class ClothesAdapter(var context: Context, clothes: ArrayList<ClothesModel>) :
    RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {
    var cloth: List<ClothesModel> = clothes
    private lateinit var analytics: FirebaseAnalytics

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.tv_name_clothes
        var tvPrice = itemView.tv_price_clothes
        var cardView = itemView.cv_clothes
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.clothes_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        analytics = FirebaseAnalytics.getInstance(context)
        val clothesItem: ClothesModel = cloth[position]
        val bundle = Bundle()

        holder.tvName.text = clothesItem.name
        holder.tvPrice.text = clothesItem.price.toString()
        holder.cardView.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: card clicked")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, clothesItem.name)
            bundle.putInt(FirebaseAnalytics.Param.PRICE, clothesItem.price!!)
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        }
    }

    override fun getItemCount(): Int {
        return cloth.size
    }
}
