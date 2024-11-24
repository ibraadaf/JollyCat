package com.id.jollycat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartModel(
    val id: String, // Cart item ID
    val catName: String,
    val price: Int,
    var quantity: Int,
    var subtotal: Int
): Parcelable
