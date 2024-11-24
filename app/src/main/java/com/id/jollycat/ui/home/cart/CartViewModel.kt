package com.id.jollycat.ui.home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.id.jollycat.domain.model.CartModel

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartModel>>()
    val cartItems: LiveData<List<CartModel>> get() = _cartItems

    init {
        // Load initial cart items from repository or database
        loadCartModels()
    }

    private fun loadCartModels() {
        // Simulated data
        val dummyCartModels = listOf(
            CartModel("1", "Whiskers", 150, 2, 300),
            CartModel("2", "Mittens", 200, 1, 200),
            CartModel("3", "Shadow", 180, 3, 540)
            // Add more items as needed
        )
        _cartItems.value = dummyCartModels
    }

    fun updateCartModel(updatedItem: CartModel) {
        // Update item in repository or database
        // For demo, update locally in ViewModel
        val updatedList = _cartItems.value?.toMutableList() ?: mutableListOf<CartModel>()
        val index = updatedList.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {
            updatedList[index] = updatedItem
            _cartItems.value = updatedList
        }
    }

    fun deleteCartModel(deletedItem: CartModel) {
        // Delete item in repository or database
        // For demo, delete locally in ViewModel
        val updatedList = _cartItems.value?.toMutableList() ?: mutableListOf()
        updatedList.removeIf { it.id == deletedItem.id }
        _cartItems.value = updatedList
    }

    fun performCheckout(checkoutId: String) {
        // Perform checkout process
        // Update Checkout ID in repository or database
        // For demo, just notify observers
    }
}