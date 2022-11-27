package com.example.oggettoonboarding.fragments.event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.repositories.UserRepository

class EventViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _eventList = MutableLiveData<List<Event>>()
    var eventList: LiveData<List<Event>> = _eventList

    fun getEvents() {
        val eventList = mutableListOf<Event>()
        val uid = userRepository.getCurrentUser()?.uid
        userRepository.getEvent(uid.toString()).addOnCompleteListener { dataSnapshot ->
            for (i in dataSnapshot.result.children) {
                i.getValue(Event::class.java)?.let { eventList.add(it) }
                Log.d("TAG", "i.event = ${i.value}")
            }
            _eventList.postValue(eventList)
        }
    }

    fun createEvent(event: Event) {
        val uid = userRepository.getCurrentUser()?.uid
        userRepository.pushEvent(event, uid.toString())
    }
}