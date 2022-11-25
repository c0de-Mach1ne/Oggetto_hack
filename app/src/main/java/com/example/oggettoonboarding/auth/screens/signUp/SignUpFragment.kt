package com.example.oggettoonboarding.auth.screens.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.auth.screens.viewmodels.AuthViewModel
import com.example.oggettoonboarding.auth.screens.viewmodels.AuthViewModelFactory
import com.example.oggettoonboarding.databinding.FragmentSignUpBinding

class SignUpFragment() : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observableViewModel()
        binding = FragmentSignUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()
        val name = binding.etName.text.toString()
        val sureName = binding.etSureName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()

        if (email.isNotBlank() && pass.isNotBlank() && confirmPass.isNotBlank()
            && name.isNotBlank() && sureName.isNotBlank() && patronymic.isNotBlank()) {
            if (pass == confirmPass) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                viewModel.signUpWithEmailAndPassword(email, pass)
                viewModel.signUpWithPersonalInfo(name, sureName, patronymic, email)
            }else Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }

    private fun observableViewModel() {
        viewModel.userState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> navigateToSingIn()
                is AuthState.Error -> Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun navigateToSingIn() =
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
}