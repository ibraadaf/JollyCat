package com.id.jollycat.ui.home.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.id.jollycat.domain.model.UserModel

class ProfileViewModel: ViewModel() {
    private val _user = MutableLiveData<UserModel>()
    val user = _user

    fun loadUser() {
        // Load user data from a repository or shared preferences
        _user.value = UserModel(userName = "JohnDoe", phoneNumber = "+1234567890")
    }

    fun logout() {
        // Perform logout logic, e.g., clearing shared preferences or database entries
    }

}