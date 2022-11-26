package com.example.oggettoonboarding.auth.fragments.edit_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.fragments.models.TechStack

class EditProfileViewModel: ViewModel() {

    private val _hobbyList = MutableLiveData<List<Hobby>>()
    var hobbyList: LiveData<List<Hobby>> = _hobbyList

    private val _techStack = MutableLiveData<List<TechStack>>()
    var techStack: LiveData<List<TechStack>> = _techStack

    fun updateHobbyList(hobbyList: List<Hobby>){
        _hobbyList.postValue(hobbyList)
    }

    fun updateTechStack(techStackList: List<TechStack>) {
        _techStack.postValue(techStackList)
    }

}