package com.example.oggettoonboarding.fragments.event

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.repositories.UserRepository
import java.util.*

class EventViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _imageUri = MutableLiveData<Uri>()
    var imageUri = _imageUri

    private val _eventList = MutableLiveData<List<Event>>()
    var eventList: LiveData<List<Event>> = _eventList

    private val _uiState = MutableLiveData<AuthState>()
    var uiState: LiveData<AuthState> = _uiState

    fun getEvents() {
        val eventList = mutableListOf<Event>()
        userRepository.getEvent().addOnCompleteListener { dataSnapshot ->
            for (i in dataSnapshot.result.children) {
                i.getValue(Event::class.java)?.let { eventList.add(it) }
                Log.d("TAG", "i.event = ${i.value}")
            }
            _eventList.postValue(eventList)
        }
    }

    fun uploadImage(fileUri: String) {
        val fileName = UUID.randomUUID().toString() + ".jpg"
        userRepository.getInstance().child(fileName).putFile(fileUri.toUri())
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    Log.d("TAG", "uri $it")
                    _imageUri.value = it
                }
            }.addOnFailureListener { e ->
                print(e.message)
            }
    }

    fun createEvent(event: Event) {
        val uid = userRepository.getCurrentUser()?.uid
        userRepository.pushEvent(event, uid.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                _uiState.value = AuthState.Success
            }
            else{
                _uiState.value = AuthState.Error(mes = it.exception?.message)
            }
        }
    }

    fun getCurrentUser() = userRepository.getCurrentUser()?.uid
}