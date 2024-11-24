package com.id.jollycat.ui.home.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.jollycat.domain.model.CatModel
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _cats = MutableLiveData<List<CatModel>>()
    val cats = _cats

    fun fetchCats() {
        viewModelScope.launch {
            val response = CatModel.dummyCats
            _cats.postValue(response)
        }
    }
}