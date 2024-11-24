package com.id.jollycat.ui.home.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.jollycat.R
import com.id.jollycat.databinding.FragmentHomeBinding
import com.id.jollycat.ui.about.AboutUsActivity
import com.id.jollycat.ui.adapter.HomeCatAdapter
import com.id.jollycat.ui.cat_detail.CatDetailActivity
import com.id.jollycat.ui.cat_detail.CatDetailActivity.Companion.DETAIL_CAT

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var rvAdapter: HomeCatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        viewModel.fetchCats()
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = HomeCatAdapter {
            Intent(requireActivity(), CatDetailActivity::class.java).apply {
                putExtra(DETAIL_CAT, it)
                startActivity(this)
            }
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.aboutUs -> {
                    Intent(requireActivity(), AboutUsActivity::class.java).apply {
                        startActivity(this)
                    }
                    true
                }
                else -> false
            }
        }

        viewModel.cats.observe(viewLifecycleOwner) {
            rvAdapter.setData(it)
        }

        with(binding) {
            homeRV.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}