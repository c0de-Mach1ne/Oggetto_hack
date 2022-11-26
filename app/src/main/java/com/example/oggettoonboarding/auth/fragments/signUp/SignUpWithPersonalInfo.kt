package com.example.oggettoonboarding.auth.fragments.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModel
import com.example.oggettoonboarding.auth.fragments.viewmodels.AuthViewModelFactory
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.databinding.FragmentSignUpPersInfoBinding

class SignUpWithPersonalInfo: Fragment(R.layout.fragment_sign_up_pers_info) {

    private lateinit var binding: FragmentSignUpPersInfoBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }
    private val args by navArgs<SignUpWithPersonalInfoArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding = FragmentSignUpPersInfoBinding.bind(view)
        binding.btnFinalRegistration.setOnClickListener {
            signUpWithPersonalInfo()
        }
    }

    private fun signUpWithPersonalInfo() {
        val name = binding.etName.text.toString()
        val sureName = binding.etSureName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()

        if (name.isNotBlank() && sureName.isNotBlank() && patronymic.isNotBlank()) {

            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            viewModel.signUpWithPersonalInfo(args.email, name, sureName, patronymic)
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }

    private fun observeViewModel() {
        viewModel.userState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> navigateToSignIn()
                is AuthState.Error -> Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun navigateToSignIn() =
        findNavController().popBackStack(R.id.signInFragment, false)
}