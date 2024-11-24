package com.id.jollycat.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.id.jollycat.R
import com.id.jollycat.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val phoneNumberEditText = binding.phoneNumber
        val registerButton = binding.registerButton
        val loginButton = binding.loginButton

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            registerViewModel.register(username, password, phoneNumber)
        }

        registerViewModel.registrationResult.observe(viewLifecycleOwner){ result ->
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            if (result == "Registration successful") {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}