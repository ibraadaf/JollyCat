package com.id.jollycat.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        // Implement your login logic here
        if (username == "user" && password == "pass") {
            _loginResult.value = true
        } else {
            _loginResult.value = false
        }
    }
}