package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nutritionapplication.databinding.VitaminsCardBinding

class VitaminsAdapter(
    private val clickListener: (VitaminsItem, View) -> Unit,
) :
    ListAdapter<VitaminsItem, VitaminsViewHolder>(object : DiffUtil.ItemCallback<VitaminsItem>(){
        override fun areItemsTheSame(oldItem: VitaminsItem, newItem: VitaminsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VitaminsItem, newItem: VitaminsItem): Boolean {
            return oldItem.name == newItem.name
        }

    }) {

    private val vitaminsList = ArrayList<VitaminsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitaminsViewHolder {
        val view = VitaminsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VitaminsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VitaminsViewHolder, position: Int) {

        holder.bind(vitaminsList[position], clickListener)

    }

    fun setListOfNutrition(itemVitamin: List<VitaminsItem>) {
        vitaminsList.clear()
        vitaminsList.addAll(itemVitamin)

    }

    override fun getItemCount(): Int {
        return vitaminsList.size
    }
}
