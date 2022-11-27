package com.example.oggettoonboarding.fragments.checklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oggettoonboarding.fragments.employee.EmployeeViewModel
import com.example.oggettoonboarding.repositories.UserRepository

class CheckListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CheckListViewModel(userRepository = UserRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}