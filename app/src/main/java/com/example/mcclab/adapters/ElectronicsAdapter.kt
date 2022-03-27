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
import com.example.mcclab.models.ElectronicsModel
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.clothes_item.view.*
import kotlinx.android.synthetic.main.electronics_item.view.*

class ElectronicsAdapter(var context: Context, electronics: ArrayList<ElectronicsModel>) :
    RecyclerView.Adapter<ElectronicsAdapter.ViewHolder>() {
    var elects: List<ElectronicsModel> = electronics
    private lateinit var analytics: FirebaseAnalytics

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.tv_name_electronics
        var tvPrice = itemView.tv_price_electronics
        var cardView = itemView.cv_electronics
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.electronics_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eletcItem: ElectronicsModel = elects[position]
        analytics = FirebaseAnalytics.getInstance(context)
        val bundle = Bundle()
        holder.tvName.text = eletcItem.name
        holder.tvPrice.text = eletcItem.price.toString()
        holder.cardView.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: card clicked")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, eletcItem.name)
            bundle.putInt(FirebaseAnalytics.Param.PRICE, eletcItem.price!!)
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        }
    }

    override fun getItemCount(): Int {
        return elects.size
    }
}