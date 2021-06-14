package com.example.nutritionapplication.nutritionAppMainFunctionality.nutritionFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionapplication.MainActivity
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionFactory
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionRepository
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionViewModel
import com.example.nutritionapplication.databinding.FragmentVitaminsBinding
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.VitaminsAdapter
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.VitaminsItem


class Vitamins : Fragment() {

    private var _binding: FragmentVitaminsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var vitaminsAdapter: VitaminsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVitaminsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewVitamins
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).showBottomNav()

        val repository = NutritionRepository()
        val factory = NutritionFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(NutritionViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw {
            binding.recyclerViewVitamins.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
        initRecyclerView()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayNutritionList() {
        viewModel.getVitamins().observe(viewLifecycleOwner, Observer {
            vitaminsAdapter.setListOfNutrition(it)
            vitaminsAdapter.notifyDataSetChanged()
        })

    }

    private fun initRecyclerView() {
        binding.recyclerViewVitamins.layoutManager = LinearLayoutManager(requireContext())
        vitaminsAdapter = VitaminsAdapter { selectedItem: VitaminsItem, cardVitamin: View ->
            listItemClicked(
                selectedItem,
                cardVitamin
            )
        }
        binding.recyclerViewVitamins.adapter = vitaminsAdapter
        vitaminsAdapter.notifyDataSetChanged()
        displayNutritionList()
    }

    private fun listItemClicked(itemVitamin: VitaminsItem, cardVitamin: View) {
        val action = VitaminsDirections.actionVitamins2ToCardContent(itemVitamin)
        findNavController().navigate(
            action,
            FragmentNavigator.Extras.Builder()
                .addSharedElements(
                    mapOf(cardVitamin to cardVitamin.transitionName,
                        )
                ).build()
        )
    }
}



