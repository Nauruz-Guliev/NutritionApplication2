package com.example.nutritionapplication.dataBase.firebaseDatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritionapplication.dataBase.firebaseDatabase.NutritionRepository
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.VitaminsItem
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminsSourcesItem

class NutritionViewModel(repository: NutritionRepository) :ViewModel() {

    private val repositoryFirebase = repository

    fun getVitamins() : MutableLiveData<ArrayList<VitaminsItem>>{
        return repositoryFirebase.getVitaminsList()
    }

    fun getVitaminSources(url: String) : MutableLiveData<ArrayList<VitaminsSourcesItem>>{
        return repositoryFirebase.getVitaminsSourcesList(url)
    }


}