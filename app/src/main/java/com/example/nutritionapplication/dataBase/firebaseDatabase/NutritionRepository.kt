package com.example.nutritionapplication.dataBase.firebaseDatabase

import androidx.lifecycle.MutableLiveData
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.VitaminsItem
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminsSourcesItem
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NutritionRepository {

    private val vitaminsFromFirebase: MutableLiveData<ArrayList<VitaminsItem>> = MutableLiveData()
    private val vitaminsSourcesFromFirebase: MutableLiveData<ArrayList<VitaminsSourcesItem>> =
        MutableLiveData()
    private lateinit var urlToFoodSource: String
    private val databaseReference = FirebaseDatabase.getInstance()
    private lateinit var firebaseReference: DatabaseReference
    private var databaseType by Delegates.notNull<Int>()

    private val listVitaminsFromFirebase = ArrayList<VitaminsItem>()
    private val listVitaminSourcesFromFirebase= ArrayList<VitaminsSourcesItem>()


    fun getVitaminsList(): MutableLiveData<ArrayList<VitaminsItem>> {
        databaseType = 1
        getProperDatabase()
        loadFireBaseData()
        return vitaminsFromFirebase
    }

    fun getVitaminsSourcesList(url: String): MutableLiveData<ArrayList<VitaminsSourcesItem>> {
        urlToFoodSource = url
        databaseType = 2
        getProperDatabase()
        loadFireBaseData()
        return vitaminsSourcesFromFirebase
    }

    private fun getProperDatabase(): DatabaseReference {

        when (databaseType) {
            1 -> {
                firebaseReference = databaseReference.getReference("vitamins_data_table")
            }
            2 -> {
                firebaseReference =
                    databaseReference.getReference("vitamins_food_sources").child(urlToFoodSource)
            }
        }
        return firebaseReference
    }


    private fun loadFireBaseData() = CoroutineScope(Dispatchers.IO).launch {

        try {
            getProperDatabase().addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (item in snapshot.children) {
                            when (databaseType) {
                                1 -> item.getValue(VitaminsItem::class.java)?.let {
                                    listVitaminsFromFirebase.add(it)
                                    vitaminsFromFirebase.value = listVitaminsFromFirebase
                                }
                                2 -> item.getValue(VitaminsSourcesItem::class.java)?.let {
                                    listVitaminSourcesFromFirebase.add(it)
                                    vitaminsSourcesFromFirebase.value =
                                        listVitaminSourcesFromFirebase
                                }
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        } catch (e: Exception) {

        }
    }

}