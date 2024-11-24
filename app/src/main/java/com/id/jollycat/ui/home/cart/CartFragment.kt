package com.id.jollycat.ui.home.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.jollycat.R
import com.id.jollycat.databinding.FragmentCartBinding
import com.id.jollycat.domain.model.CartModel
import com.id.jollycat.ui.adapter.CartAdapter
import java.util.UUID

class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val btnCheckout = binding.btnCheckout

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CartAdapter(emptyList(),
            onUpdateClick = { updatedItem -> updateCartModel(updatedItem) },
            onDeleteClick = { deletedItem -> deleteCartModel(deletedItem) }
        )
        recyclerView.adapter = adapter

        cartViewModel.cartItems.observe(viewLifecycleOwner){ cartItems ->
            adapter.apply {
                this.cartItems = cartItems
                notifyDataSetChanged()
            }
        }

        btnCheckout.setOnClickListener {
            performCheckout()
        }
    }

    private fun updateCartModel(updatedItem: CartModel) {
        // Update item in ViewModel or repository
        cartViewModel.updateCartModel(updatedItem)
    }

    private fun deleteCartModel(deletedItem: CartModel) {
        // Delete item in ViewModel or repository
        cartViewModel.deleteCartModel(deletedItem)
    }

    private fun performCheckout() {
        val checkoutId = UUID.randomUUID().toString()
        // Call ViewModel method to perform checkout
        cartViewModel.performCheckout(checkoutId)

        // Show notification to user (example: Toast)
        val message = "Checkout successful. Checkout ID: $checkoutId"
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}