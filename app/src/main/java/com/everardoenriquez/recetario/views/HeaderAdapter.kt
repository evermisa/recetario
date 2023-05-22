package com.everardoenriquez.recetario.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.everardoenriquez.recetario.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.header_recipe_list_item, parent, false)

        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(0)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //private val flowerNumberTextView: TextView = itemView.findViewById(R.id.flower_number_text)

        fun bind(recipeCount: Int) {
            //flowerNumberTextView.text = flowerCount.toString()
        }
    }
}