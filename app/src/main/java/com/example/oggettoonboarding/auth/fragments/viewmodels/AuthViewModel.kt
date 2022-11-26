package com.example.oggettoonboarding.auth.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.auth.models.UserAuth
import com.example.oggettoonboarding.auth.models.UserPersonalInfo
import com.example.oggettoonboarding.repositories.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _userState = MutableLiveData<AuthState>()
    val userState: LiveData<AuthState> = _userState

    fun signIn(email: String, pass: String) {
        authRepository.signIn(UserAuth(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                _userState.value = AuthState.Success
            } else {
                _userState.value = AuthState.Error(mes = it.exception?.message)
            }
        }
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        authRepository.signUpWithEmailAndPass(
            UserAuth(email, password)
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                _userState.value = AuthState.Success
            }
            else _userState.value = AuthState.Error(mes = it.exception?.message)
        }
    }

    fun signUpWithPersonalInfo(
        email: String,
        name: String,
        sureName: String,
        patronymic: String,
    ) {
        authRepository.signUpWithPersonalInfo(UserPersonalInfo(
            email,
            name,
            sureName,
            patronymic,
        )
        )?.addOnCompleteListener {
            if (it.isSuccessful) _userState.value = AuthState.Success
            else _userState.value = AuthState.Error(mes = it.exception?.message)
        }
    }
}
