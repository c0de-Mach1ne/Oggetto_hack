package com.example.oggettoonboarding.auth.fragments.signIn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModel
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModelFactory
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
            navigateToSignUp()
        }
    }

    private fun observableViewModel() {
        viewModel.userState.observe(viewLifecycleOwner){
            when(it){
                is AuthState.Success -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    navigateToTabs()
                }
                is AuthState.Error -> {
                    Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        if (email.isNotBlank() && pass.isNotBlank()) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            viewModel.signIn(email, pass)
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToSignUp() = findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)

    private fun navigateToTabs() =
        findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
}