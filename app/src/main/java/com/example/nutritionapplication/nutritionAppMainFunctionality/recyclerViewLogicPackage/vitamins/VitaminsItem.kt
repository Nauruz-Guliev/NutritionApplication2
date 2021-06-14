package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins

import android.os.Parcelable
import com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView.VitaminsSourcesItem
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
class VitaminsItem(

    var name: String,


    var title: String,


    var description: String,


    var amountOfNutrition: String,


    var amountLimitNutrition: String,


    var imageUrl: String,


    var didYouKnow: String
): Parcelable {
    constructor() : this("","", "", "", "", "", "") {
    }
}


