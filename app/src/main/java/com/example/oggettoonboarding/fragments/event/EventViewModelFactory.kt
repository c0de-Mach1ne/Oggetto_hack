package com.example.oggettoonboarding.fragments.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oggettoonboarding.fragments.employee.EmployeeViewModel
import com.example.oggettoonboarding.repositories.UserRepository

class EventViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventViewModel(userRepository = UserRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}