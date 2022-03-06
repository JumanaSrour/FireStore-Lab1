package com.example.mcclab.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mcclab.R
import com.example.mcclab.activities.AddContactActivity
import com.example.mcclab.models.ContactModel
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter(var context: Context, contacts: List<ContactModel>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    var contact: List<ContactModel> = contacts

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.tv_name
        var tvAddress = itemView.tv_address
        var tvPhone = itemView.tv_phone
        var cardView = itemView.cardView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val a: ContactModel = contact[position]
        holder.tvName.text = a.name
        holder.tvAddress.text = a.address
        holder.tvPhone.text = a.phone
        holder.cardView.setOnClickListener {
            val intent = Intent(context, AddContactActivity::class.java)
            intent.putExtra("name", a.name)
            intent.putExtra("address", a.address)
            intent.putExtra("phone", a.phone)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contact.size
    }
}
