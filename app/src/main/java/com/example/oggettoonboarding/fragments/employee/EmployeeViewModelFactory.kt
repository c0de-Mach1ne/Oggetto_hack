package com.example.oggettoonboarding.fragments.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oggettoonboarding.repositories.UserRepository

class EmployeeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(userRepository = UserRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}