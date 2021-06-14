package com.example.nutritionapplication.dataBase.firebaseDatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NutritionFactory (private val repository: NutritionRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NutritionViewModel::class.java)){
            return NutritionViewModel(repository) as T
        }
        else{
            throw IllegalArgumentException("Unknown viewModel class!")
        }

    }

}