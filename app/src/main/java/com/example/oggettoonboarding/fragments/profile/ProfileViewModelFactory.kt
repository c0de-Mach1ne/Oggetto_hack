package com.example.oggettoonboarding.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oggettoonboarding.repository.AuthRepository

class ProfileViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(authRepository = AuthRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}