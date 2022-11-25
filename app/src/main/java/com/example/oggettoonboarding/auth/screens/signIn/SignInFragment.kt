package com.example.oggettoonboarding.auth.screens.signIn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.screens.viewmodels.AuthViewModel
import com.example.oggettoonboarding.auth.screens.viewmodels.AuthViewModelFactory
import com.example.oggettoonboarding.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        observableViewModel()
        binding.btnSignIn.setOnClickListener {
            signIn()
        }
        binding.btnSignUp.setOnClickListener {

        }
    }

    private fun observableViewModel() {

    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        if (email.isNotBlank() && pass.isNotBlank()) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            viewModel.signIn(email, pass)
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }
}