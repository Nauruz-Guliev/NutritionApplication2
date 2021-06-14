package com.example.nutritionapplication.nutritionAppMainFunctionality.recyclerViewLogicPackage.vitamins.foodSourcesRecyclerView

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VitaminsSourcesItem(
  var foodName: String,
  var foodImageUrl: String,
  var amountDaily: String
): Parcelable {
  constructor() : this("", "", "")
}
