package com.id.jollycat.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID
import java.util.regex.Pattern
import java.util.regex.Pattern.*

class RegisterViewModel : ViewModel() {

    private val _registrationResult = MutableLiveData<String>()
    val registrationResult: LiveData<String> = _registrationResult

    private val registeredUsers = mutableListOf<String>()

    private val _success = MutableLiveData<Boolean>()
    val success = _success

    fun register(username: String, password: String, phoneNumber: String) {
        when {
            username.isBlank() || password.isBlank() || phoneNumber.isBlank() -> {
                _registrationResult.value = "All fields must be filled"
            }
            username.length < 8 -> {
                _registrationResult.value = "Username must be at least 8 characters long"
            }
            !matches("^[a-zA-Z0-9]*$", username) -> {
                _registrationResult.value = "Username must consist of alphanumeric characters only"
            }
            registeredUsers.contains(username) -> {
                _registrationResult.value = "Username is already taken"
            }
            password.length < 5 -> {
                _registrationResult.value = "Password must be at least 5 characters long"
            }
            !matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{5,}\$", password) -> {
                _registrationResult.value = "Password must contain at least 1 letter, 1 number, and 1 symbol"
            }
            phoneNumber.length !in 8..20 -> {
                _registrationResult.value = "Phone number must be between 8-20 characters long"
            }
            !phoneNumber.startsWith("0") && !phoneNumber.startsWith("+") -> {
                _registrationResult.value = "Phone number must start with '0' or '+'"
            }
            !matches("^[0+][0-9]{7,19}\$", phoneNumber) -> {
                _registrationResult.value = "Phone number can only consist of digits after the first character"
            }
            else -> {
                _success.postValue(true)
                val userId = UUID.randomUUID().toString()
                // Add user to database here
                registeredUsers.add(username)
                _registrationResult.value = "Registration successful"
            }
        }
    }
}