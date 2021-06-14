package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutritionapplication.R
import com.example.nutritionapplication.databinding.CardVitiaminsSourcesBinding
import com.example.nutritionapplication.databinding.FragmentVitaminsCardContentBinding
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminSourcesAdapter.VitaminSourcesViewHolder
import kotlinx.android.synthetic.main.vitamins_card.view.*

class VitaminSourcesAdapter(
    private val clickListener: (VitaminsSourcesItem) -> Unit
) :
    RecyclerView.Adapter<VitaminSourcesAdapter.VitaminSourcesViewHolder>() {

    private val vitaminsSourcesList = ArrayList<VitaminsSourcesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitaminSourcesViewHolder {
        val view =CardVitiaminsSourcesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VitaminSourcesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return vitaminsSourcesList.size
    }

    override fun onBindViewHolder(holder: VitaminSourcesViewHolder, position: Int) {
        holder.bind(vitaminsSourcesList[position], clickListener)
    }

    fun setListOfFoodSources(itemVitamin: List<VitaminsSourcesItem>) {
        vitaminsSourcesList.clear()
        vitaminsSourcesList.addAll(itemVitamin)
    }

    inner class VitaminSourcesViewHolder(val binding: CardVitiaminsSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            vitaminsSourcesItem: VitaminsSourcesItem,
            clickListener: (VitaminsSourcesItem) -> Unit
        ) {
            binding.sourcesItem = vitaminsSourcesItem
            Glide.with(itemView)
                .load(vitaminsSourcesItem.foodImageUrl)
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .into(binding.vitaminsFoodSourceImageView)

            binding.vitSourcesLayout.relativeLayout?.setOnClickListener {
                clickListener(vitaminsSourcesItem)
            }

        }
    }


}
