package com.example.oggettoonboarding.fragments.employee

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.fragments.models.User
import com.example.oggettoonboarding.repositories.UserRepository

class EmployeeViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    var userList: LiveData<List<User>> = _userList

    fun getUsers() {
        val listUser = mutableListOf<User>()
        userRepository.getUsers().addOnCompleteListener { dataSnapshot ->
            for (i in dataSnapshot.result.children) {
                i.getValue(User::class.java)?.let { listUser.add(it) }
                Log.d("TAG" , "i.value = ${i.value}")
            }
            _userList.postValue(listUser)
        }
    }
}