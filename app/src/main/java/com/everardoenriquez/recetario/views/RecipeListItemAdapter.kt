package com.everardoenriquez.recetario.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.everardoenriquez.recetario.R
import com.example.testkot.Models.RecipeModel

class RecipeListItemAdapter(private val recipeList:List<RecipeModel>,
                            private val onClick: (RecipeModel) -> Unit) :
    RecyclerView.Adapter<RecipeListItemAdapter.RecipeViewHolder>() {
//class RecipeListItemAdapter(private val onClick: (RecipeModel) -> Unit) :
  //  ListAdapter<RecipeModel, RecipeListItemAdapter.ViewHolder>(RecipeCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)

        return RecipeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        //val recipe = getItem(position)
        //holder.bind(recipe)
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class RecipeViewHolder(itemView: View, val onClick: (RecipeModel) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeListItemPhoto)
        private val recipeName: TextView = itemView.findViewById(R.id.recipeListItemName)
        private var currentRecipe: RecipeModel? = null

        init {
            itemView.setOnClickListener {
                currentRecipe?.let {
                    onClick(it)
                }
            }
        }

        fun bind(recipe:RecipeModel) {
            recipeName.text = recipe.recipeName
            val filePhoto = itemView.getResources().getIdentifier("pizza", "drawable","com.everardoenriquez.recetario")
            recipeImage.setImageResource(filePhoto)
        }
    }
}
/*
object RecipeCallback : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem.recipeName == newItem.recipeName
    }
}*/