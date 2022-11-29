package com.example.oggettoonboarding.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.repositories.AuthRepository

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _userState = MutableLiveData<AuthState>()
    var userState: LiveData<AuthState> = _userState

    init {
        authSignInUser()
    }

    private fun authSignInUser() {
        if (authRepository.getAuthUser() != null) {
            _userState.value = AuthState.Success
        } else {
            _userState.value = AuthState.Error(mes = "Error!")
        }
    }
}