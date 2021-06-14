package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.nutritionapplication.MainActivity
import com.example.nutritionapplication.R
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionFactory
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionRepository
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionViewModel
import com.example.nutritionapplication.databinding.FragmentVitaminsCardContentBinding
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminSourcesAdapter
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminsSourcesItem


class VitaminsContent : Fragment() {

    val args: VitaminsContentArgs by navArgs()
    private lateinit var binding: FragmentVitaminsCardContentBinding

    private lateinit var urlToFoodSource:String

    private lateinit var viewModel: NutritionViewModel
    private lateinit var recyclerViewFood: RecyclerView
    private lateinit var vitaminsFoodSourcesAdapter: VitaminSourcesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).hideBottomNav()
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vitamins_card_content, container, false)
        recyclerViewFood = binding.sourcesRecyclerView

        val repository = NutritionRepository()
        val factory = NutritionFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(NutritionViewModel::class.java)

        animateShared()
        animateItems()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun animateItems(){
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateShared()
        binding.vitaminsItem = args.vitamin
        urlToFoodSource = args.vitamin.name
        Glide.with(requireContext()).load(args.vitamin.imageUrl).centerCrop().into(binding.imageViewVitamin)
        binding.executePendingBindings()
        initRecyclerView()
    }

    private fun animateShared() {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
    }


    private fun initRecyclerView() {
        binding.sourcesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        vitaminsFoodSourcesAdapter = VitaminSourcesAdapter( { SelectedItem: VitaminsSourcesItem -> vitaminSourceClicked(SelectedItem) })
        binding.sourcesRecyclerView.adapter = vitaminsFoodSourcesAdapter
        displayNutritionList()
    }

    private fun vitaminSourceClicked(itemVitamin: VitaminsSourcesItem) {

    }

    private fun displayNutritionList() {
        viewModel.getVitaminSources(urlToFoodSource).observe(viewLifecycleOwner, Observer {
            vitaminsFoodSourcesAdapter.setListOfFoodSources(it)
            vitaminsFoodSourcesAdapter.notifyDataSetChanged()
        })
    }
}