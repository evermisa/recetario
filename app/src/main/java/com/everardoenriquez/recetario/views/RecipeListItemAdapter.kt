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

class RecipeListItemAdapter(private var recipeList:List<RecipeModel>,
                            private val onClick: (RecipeModel) -> Unit) :
    RecyclerView.Adapter<RecipeListItemAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)


        return RecipeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun onFilter(recipeList:List<RecipeModel>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class RecipeViewHolder(itemView: View, val onClick: (RecipeModel) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeListItemPhoto)
        private val recipeName: TextView = itemView.findViewById(R.id.recipeListItemName)
        private var currentRecipe: RecipeModel? = null
        val imageType = "drawable"

        init {
            itemView.setOnClickListener {
                currentRecipe?.let {
                    onClick(it)
                }
            }
        }

        fun bind(recipe:RecipeModel) {
            currentRecipe = recipe

            recipeName.text = recipe.recipeName
            val filePhoto = itemView.getResources().getIdentifier(
                recipe.imageName, imageType,itemView.context.packageName
            )
            recipeImage.setImageResource(filePhoto)
        }
    }
}