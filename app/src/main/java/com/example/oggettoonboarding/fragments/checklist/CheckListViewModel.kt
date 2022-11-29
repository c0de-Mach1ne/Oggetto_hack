package com.example.oggettoonboarding.fragments.checklist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.fragments.models.CheckList
import com.example.oggettoonboarding.fragments.models.UserCheckList
import com.example.oggettoonboarding.repositories.UserRepository

class CheckListViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _checkList = MutableLiveData<List<CheckList>>()
    var checkList: LiveData<List<CheckList>> = _checkList

    fun generateUserCheckList() {
        val userCheckList = mutableListOf<CheckList>()
        userRepository.getStandardCheckList().addOnCompleteListener { dataSnapshot ->
            for (i in dataSnapshot.result.children) {
                i.getValue(CheckList::class.java)?.let { userCheckList.add(it) }
                Log.d("TAG", "i.value = ${i.value}")
            }
            val userUid = userRepository.getCurrentUser()?.uid
            userRepository.initUserCheckList(UserCheckList(userUid, userCheckList))
            _checkList.postValue(userCheckList)
        }
    }

    fun generateCheckList() {
        userRepository.pushStandardUserCheckList(listOf
            (
            CheckList("Заполнить анкету",
                "Это нужно для того, чтобы другие сотрудники могли лучше узнать тебя",
                false),

            CheckList("Ознакомиться с интерфейсом приложенния",
                "Ты познакомишься с функционалом приложения и откроешь для себя новые возможности",
                false),

            CheckList("Выиграть в 1 игре",
                "У тебя всё получится!",
                false),

            CheckList("Изучить 5 анкет сотрудников",
                "Хей хей, ты можешь себе представить 130 разных сотрудников? Это сложно, но давай начнем со знакомство с 5-ю!",
                false),

            CheckList("Получить приветственный пак",
                "Потрясающе, ты огромный молодец. Получи свой стартовый пак у HR-а",
                false),
        ))
    }
}