package com.example.testroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testroom.R

class ItemAdapter(private val listItem: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.item)
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
        holder.tvItem.text = item
    }
}