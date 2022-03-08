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

class ContactAdapter(var context: Context, contacts: ArrayList<ContactModel>) :
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
        val contactItem: ContactModel = contact[position]
        holder.tvName.text = contactItem.name
        holder.tvAddress.text = contactItem.address
        holder.tvPhone.text = contactItem.phone.toString()
        holder.cardView.setOnClickListener {
            val intent = Intent(context, AddContactActivity::class.java)
            intent.putExtra("name", contactItem.name)
            intent.putExtra("address", contactItem.address)
            intent.putExtra("phone", contactItem.phone)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contact.size
    }
}
