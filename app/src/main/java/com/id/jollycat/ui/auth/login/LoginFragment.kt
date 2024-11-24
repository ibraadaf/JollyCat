package com.id.jollycat.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.id.jollycat.R
import com.id.jollycat.databinding.FragmentLoginBinding
import com.id.jollycat.ui.home.HomeActivity

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.loginButton

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginViewModel.login(username, password)
        }

        binding.registerButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                Intent(requireActivity(), HomeActivity::class.java).run {
                    startActivity(this)
                    requireActivity().finish()
                }
            } else {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}