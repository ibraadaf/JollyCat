package com.id.jollycat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.id.jollycat.databinding.RvItemCatBinding
import com.id.jollycat.domain.model.CatModel

class HomeCatAdapter (private val onClick: (CatModel) -> Unit = {}): RecyclerView.Adapter<HomeCatAdapter.HomeViewHolder>() {
    private val cats = mutableListOf<CatModel>()

    class HomeViewHolder(val binding: RvItemCatBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = RvItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = cats[position]
        with(holder.binding) {
            rvItemCat.setOnClickListener {
                onClick(item)
            }
            catName.text = item.name
            catPrice.text = item.price.toString()
            catDescription.text = item.description
        }
    }

    fun setData(list: List<CatModel>) {
        cats.clear()
        cats.addAll(list)
        notifyDataSetChanged()
    }
}