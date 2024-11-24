package com.id.jollycat.ui.home.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.id.jollycat.R
import com.id.jollycat.databinding.FragmentProfileBinding
import com.id.jollycat.ui.auth.AuthActivity

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        profileViewModel.loadUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvUsername = binding.tvUsername
        val tvPhoneNumber = binding.tvPhoneNumber
        val btnLogout = binding.btnLogout

        profileViewModel.user.observe(viewLifecycleOwner) {
            tvUsername.text = it.userName
            tvPhoneNumber.text = it.phoneNumber
        }


        btnLogout.setOnClickListener {
            profileViewModel.logout()
            navigateToLogout()
        }
    }

    private fun navigateToLogout() {
        Intent(requireActivity(), AuthActivity::class.java).run {
            startActivity(this)
            requireActivity().finish()
        }
    }
}