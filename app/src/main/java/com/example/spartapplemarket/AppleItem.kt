package com.example.spartapplemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AppleItem(var picture: Int, var title: String, var location: String,  var price: Int, var comment: Int, var like: Int)

@Parcelize
data class Product(var picture: Int, var title: String, var location: String,  var price: Int) :
    Parcelable