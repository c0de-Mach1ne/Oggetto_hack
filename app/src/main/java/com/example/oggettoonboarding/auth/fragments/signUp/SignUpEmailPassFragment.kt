package com.example.oggettoonboarding.auth.fragments.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModel
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModelFactory
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.databinding.FragmentSignUpEmailPassBinding

class SignUpEmailPassFragment() : Fragment(R.layout.fragment_sign_up_email_pass) {

    private lateinit var binding: FragmentSignUpEmailPassBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }
    private lateinit var email: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observableViewModel()
        binding = FragmentSignUpEmailPassBinding.bind(view)

        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()

        if (email.isNotBlank() && pass.isNotBlank() && confirmPass.isNotBlank()) {
            if (pass == confirmPass) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                viewModel.signUpWithEmailAndPassword(email, pass)
            }
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }

    private fun observableViewModel() {
        viewModel.userState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> navigateToSignUpPersonalInfo()
                is AuthState.Error -> Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun navigateToSignUpPersonalInfo() =
        findNavController().navigate(SignUpEmailPassFragmentDirections.
        actionSignUpFragmentToSignUpWithPersonalInfo(email))
}