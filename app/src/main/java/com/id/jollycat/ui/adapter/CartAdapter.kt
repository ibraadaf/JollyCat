package com.id.jollycat.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.id.jollycat.databinding.RvItemCartBinding
import com.id.jollycat.domain.model.CartModel

class CartAdapter(
    var cartItems: List<CartModel>,
    private val onUpdateClick: (CartModel) -> Unit,
    private val onDeleteClick: (CartModel) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = RvItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        with(holder.binding) {
            tvCatName.text = cartItem.catName
            tvPrice.text = "Price: \$${cartItem.price}"
            etQuantity.setText(cartItem.quantity.toString())
            tvSubtotal.text = "Subtotal: \$${cartItem.subtotal}"

            btnUpdate.setOnClickListener {
                val newQuantity = etQuantity.text.toString().toIntOrNull()
                if (newQuantity == null || newQuantity <= 0) {
                    holder.showToast("Invalid quantity. Quantity must be a number greater than 0.")
                } else {
                    cartItem.quantity = newQuantity
                    cartItem.subtotal = cartItem.price * newQuantity
                    onUpdateClick(cartItem)
                }
            }

            btnDelete.setOnClickListener {
                onDeleteClick(cartItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    class CartViewHolder(val binding: RvItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showToast(message: String) {
            Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
