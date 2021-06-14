package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutritionapplication.R
import com.example.nutritionapplication.databinding.VitaminsCardBinding
import kotlinx.android.synthetic.main.vitamins_card.view.*

class VitaminsViewHolder(private val binding: VitaminsCardBinding) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(vitaminsItem: VitaminsItem, clickListener: (VitaminsItem, View) -> Unit) {

        binding.vitaminsItem = vitaminsItem
        Glide.with(itemView)
            .load(vitaminsItem.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .into(binding.vitaminImageView)

        binding.relativeLayout.relativeLayout?.setOnClickListener {
            clickListener(vitaminsItem, binding.nutritionCard)
        }
    }


}
