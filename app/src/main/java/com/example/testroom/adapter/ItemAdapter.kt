package com.example.testroom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testroom.ItemDetail
import com.example.testroom.R
import com.example.testroom.data.Item

class ItemAdapter(private val listItem: List<Item>): RecyclerView.Adapter<ItemAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_row,
                    parent,
                    false
                )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listItem[position]
        holder.tvItem.text = item.itemName

        // set if list is clicked
        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, ItemDetail::class.java)
            intentDetail.putExtra("item_id", item.id)
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}