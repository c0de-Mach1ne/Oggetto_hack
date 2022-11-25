package com.example.oggettoonboarding.auth.models


sealed class AuthState {
    object Success : AuthState()
    data class Error(val mes: String?) : AuthState()
}