package com.id.jollycat.ui.cat_detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.id.jollycat.R
import com.id.jollycat.databinding.ActivityCatDetailBinding
import com.id.jollycat.domain.model.CatModel

class CatDetailActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_CAT = "detailCat"
    }
    private lateinit var binding: ActivityCatDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.main.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val getCatData = intent.getParcelableExtra<CatModel>(DETAIL_CAT) ?: CatModel.dummyCats[0]

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setCatDetail(getCatData)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setCatDetail(catModel: CatModel) {
        with(binding) {
            tvCatName.text = catModel.name
            tvDescription.text = catModel.description
            tvPrice.text = catModel.price.toString()
        }
    }
}